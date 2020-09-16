package asiantech.internship.winter.musicplayer

import android.Manifest
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.winter.musicplayer.model.Song
import asiantech.internship.winter.musicplayer.playback.*
import kotlinx.android.synthetic.`at-trinhnguyen`.activity_music.*


class MusicActivity : AppCompatActivity(), View.OnClickListener, SongAdapter.SongClicked {

    private var musicService: MusicService? = null
    private var isBound: Boolean? = null
    private var playerAdapter: PlayerAdapter? = null
    private var userIsSeeking = false
    private var playbackListener: PlaybackListener? = null
    private var deviceSongs = mutableListOf<Song>()
    private var musicNotificationManager: MusicNotificationManager? = null
    private var songAdapter: SongAdapter? = null
    private var seekBar: SeekBar? = null
    private var lastSong: Song? = null
    private var rotate: ObjectAnimator? = null

    companion object {
        private var isOnCreateFirstTime = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        songAdapter = SongAdapter(this)
        doBindService()
        initViews()
        initSeekBar()

        if (!isOnCreateFirstTime) {
            restoreLastSongPlay()
            isOnCreateFirstTime = true
        }
    }

    override fun onStart() {
        super.onStart()
        playerAdapter?.apply {
            if (isPlaying()) {
                restorePlayerStatus()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        doUnbindService()
        playerAdapter?.apply {
            if (isMediaPlayer()) {
                onPauseActivity()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        doBindService()
        playerAdapter?.apply {
            if (isPlaying()) {
                restorePlayerStatus()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            finish()
        } else getMusic()
    }

    private fun initViews() {
        seekBar = findViewById(R.id.seekBar)
        imgBtnPlay.setOnClickListener(this)
        imgBtnNext.setOnClickListener(this)
        imgBtnPrevious.setOnClickListener(this)
        view.setOnClickListener(this)
        deviceSongs = SongProvider.getAllDeviceSongs(this)
        songAdapter?.setOnSongClicked(this)

        recyclerView.apply {
            adapter = songAdapter
            hasFixedSize()
        }

        tbShuffle.setOnCheckedChangeListener { _, isChecked ->
            musicService?.mediaPlayerHolder?.setShuffle(isChecked)
        }



        if (deviceSongs.isNotEmpty()) {
            try {
                lastSong = deviceSongs[0]
                tvSongTitle.text = ""
                tvArtist.text = ""
                tvDurationLeft.text = ""
                tvDurationRight.text = ""
//                seekBar?.max = deviceSongs[0].duration
//                imgSongArtCurrent.setImageBitmap(deviceSongs[0].path?.let { Utils.songArt(it, this@MusicActivity) })
            } catch (e: IndexOutOfBoundsException) {
                e.printStackTrace()
            }
        }



        rotate = ObjectAnimator.ofFloat(imgSongArtCurrent, "rotation", 0f, 360f).apply {
            duration = 15000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            interpolator = LinearInterpolator()
            start()
            pause()
        }

    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {

            musicService = (iBinder as MusicService.LocalBinder).instance
            playerAdapter = musicService?.mediaPlayerHolder
            musicNotificationManager = musicService?.musicNotificationManager

            if (playbackListener == null) {
                playbackListener = PlaybackListener()
                playerAdapter?.setPlaybackInfoListener(playbackListener)
            }

            playerAdapter?.apply {
                if (isPlaying()) {
                    restorePlayerStatus()
                }
            }
            checkReadStoragePermissions()
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            musicService = null
        }
    }

    private fun checkReadStoragePermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        } else getMusic()
    }

    private fun getMusic() {
        deviceSongs.clear()
        deviceSongs.addAll(SongProvider.getAllDeviceSongs(this))
        songAdapter?.submitSongs(deviceSongs)
    }

    private fun updatePlayingInfo(isRestore: Boolean, isStartPlay: Boolean) {

        if (isStartPlay) {
            playerAdapter?.getMediaPlayer()?.start()
            Handler().postDelayed({
                musicService?.startForeground(MusicNotificationManager.NOTIFICATION_ID,
                        musicNotificationManager?.createNotification())
            }, 200)
        }

        playerAdapter?.getCurrentSong()?.let { currentSong ->
            if (lastSong != currentSong) {
                lastSong = currentSong
                tvSongTitle.text = currentSong.title
                tvArtist.text = currentSong.artistName
                seekBar?.max = currentSong.duration
                imgSongArtCurrent.setImageBitmap(currentSong.path?.let { Utils.songArt(it, this@MusicActivity) })
            }
        }

        musicService?.mediaPlayerHolder?.apply {
            if (isShuffle()) {
                tbShuffle.isChecked = true
            }
        }

        if (isRestore) {
            playerAdapter?.getPlayerPosition()?.let {
                seekBar?.progress = it
            }
            updatePlayingStatus()
            Handler().postDelayed({
                //stop foreground if coming from pause state
                musicService?.let {
                    if (it.isRestoredFromPause) {
                        musicService?.stopForeground(false)
                        musicService?.musicNotificationManager?.notificationManager
                                ?.notify(MusicNotificationManager.NOTIFICATION_ID,
                                        musicService?.musicNotificationManager?.notificationBuilder?.build())
                        musicService?.isRestoredFromPause = false
                    }
                }
            }, 200)
        }
    }

    private fun updatePlayingStatus() {
        val drawable: Int
        if (playerAdapter?.getState() != PlaybackInfoListener.State.PAUSED) {
            drawable = R.drawable.ic_media_pause
            rotate?.resume()
        } else {
            drawable = R.drawable.ic_media_play
            rotate?.pause()
        }
        imgBtnPlay.post { imgBtnPlay.setImageResource(drawable) }
    }

    private fun restorePlayerStatus() {
        playerAdapter?.isMediaPlayer()?.let {
            seekBar?.isEnabled = it
        }

        //if we are playing and the activity was restarted
        //update the action_player_full panel
        playerAdapter?.let {
            if (it.isMediaPlayer()) {
                it.onResumeActivity()
                updatePlayingInfo(isRestore = true, isStartPlay = false)
            }
        }
    }

    private fun doBindService() {
        // Establish a connection with the service.  We use an explicit
        // class name because we want a specific service implementation that
        // we know will be running in our own process (and thus won't be
        // supporting component replacement by other applications).
        bindService(Intent(this,
                MusicService::class.java), connection, Context.BIND_AUTO_CREATE)
        isBound = true

        val startNotStickyIntent = Intent(this, MusicService::class.java)
        startService(startNotStickyIntent)
    }

    private fun doUnbindService() {
        isBound?.let {
            if (it) {
                unbindService(connection)
                isBound = false
            }
        }
    }

    private fun onSongSelected(song: Song, songs: List<Song>) {
        seekBar?.let {
            if (!it.isEnabled) {
                it.isEnabled = true
            }
        }

        try {
            playerAdapter?.setCurrentSong(song, songs)
            val sharedPref = applicationContext?.getSharedPreferences(getString(R.string.shared_prefs_file_name), Context.MODE_PRIVATE)
                    ?: return
            with(sharedPref.edit()) {
                deviceSongs.indexOf(playerAdapter?.getCurrentSong()).let {
                    if (it != -1) {
                        putInt(getString(R.string.shared_prefs_song_index), it)
                    }
                }
                commit()
            }
            playerAdapter?.initMediaPlayer()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun skipPrev() {
        checkIsPlayer()?.let {
            if (it) {
                playerAdapter?.instantReset()
            }
        }
    }

    private fun resumeOrPause() {
        checkIsPlayer()?.also {
            if (it) {
                playerAdapter?.resumeOrPause()
            } else {
                val songs = deviceSongs
                if (songs.isNotEmpty()) {
                    onSongSelected(songs[0], songs)
                }
            }
        }
    }

    private fun skipNext() {
        checkIsPlayer()?.let {
            if (it) {
                playerAdapter?.skip(true)
            }
        }
    }

    private fun checkIsPlayer(): Boolean? {
        return playerAdapter?.isMediaPlayer()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgBtnPlay -> {
                resumeOrPause()
            }
            R.id.imgBtnNext -> {
                skipNext()
            }
            R.id.imgBtnPrevious -> {
                skipPrev()
            }
            R.id.view -> {
                restoreLastSongPlay()
            }
        }
    }

    private fun restoreLastSongPlay() {
        getSharedPreferences(getString(R.string.shared_prefs_file_name), Context.MODE_PRIVATE)?.apply {
            val songIndex = getInt(getString(R.string.shared_prefs_song_index), 0)
            recyclerView.postDelayed({
                if (songIndex in deviceSongs.indices) {
                    onSongSelected(deviceSongs[songIndex], deviceSongs)
                    (recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(songIndex, 160)
                }
            }, 2000)
        }
    }

    private fun initSeekBar() {
        seekBar?.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    var userSelectedPosition = 0

                    override fun onStartTrackingTouch(seekBar: SeekBar) {
                        userIsSeeking = true
                    }

                    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                        if (fromUser) {
                            userSelectedPosition = progress
                        }
                        tvDurationLeft.text = Utils.formatDuration(progress)
                        tvDurationRight.text = playerAdapter?.getCurrentSong()?.duration?.let { duration ->
                            Utils.formatDuration(duration - progress)
                        }
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar) {

                        if (userIsSeeking) {

                        }
                        userIsSeeking = false
                        playerAdapter?.seekTo(userSelectedPosition)
                    }

                })
    }

    override fun onSongClicked(song: Song) {
        onSongSelected(song, deviceSongs)
    }

    internal inner class PlaybackListener : PlaybackInfoListener() {

        override fun onPositionChanged(position: Int) {
            if (!userIsSeeking) {
                seekBar?.progress = position
            }
        }

        override fun onStateChanged(@State state: Int) {
            updatePlayingStatus()
            if (playerAdapter?.getState() != State.PAUSED) {
                updatePlayingInfo(isRestore = false, isStartPlay = true)
            }
        }

        override fun onPlaybackCompleted() {
            //After playback is complete
        }
    }
}
