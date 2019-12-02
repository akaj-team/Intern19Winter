package asiantech.internship.winter.musicplayer

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import asiantech.internship.summer.R
import asiantech.internship.winter.musicplayer.Utils.SongProvider
import asiantech.internship.winter.musicplayer.Utils.Utils
import asiantech.internship.winter.musicplayer.model.Song
import asiantech.internship.winter.musicplayer.playback.MusicNotificationManager
import asiantech.internship.winter.musicplayer.playback.MusicService
import asiantech.internship.winter.musicplayer.playback.PlaybackInfoListener
import asiantech.internship.winter.musicplayer.playback.PlayerAdapter
import kotlinx.android.synthetic.`at-trinhnguyen`.activity_music.*
import kotlinx.android.synthetic.`at-trinhnguyen`.controls.*

class MusicActivity : AppCompatActivity(), View.OnClickListener, SongAdapter.SongClicked {

    private var musicService: MusicService? = null
    private var isBound: Boolean? = null
    private var playerAdapter: PlayerAdapter? = null
    private var userIsSeeking = false
    private var playbackListener: PlaybackListener? = null
    private var deviceSongs = mutableListOf<Song>()
    private var musicNotificationManager: MusicNotificationManager? = null
    private lateinit var songAdapter: SongAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        songAdapter = SongAdapter(this)
        doBindService()
        initViews()
        initSeekBar()


    }

    override fun onPause() {
        super.onPause()
        doUnbindService()
        if (playerAdapter != null && playerAdapter!!.isMediaPlayer()) {
            playerAdapter!!.onPauseActivity()
        }
    }

    override fun onResume() {
        super.onResume()
        doBindService()
        if (playerAdapter != null && playerAdapter!!.isPlaying()) {

            restorePlayerStatus()
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
        imgBtnPlay.setOnClickListener(this)
        imgBtnNext.setOnClickListener(this)
        imgBtnPrevious!!.setOnClickListener(this)
        deviceSongs = SongProvider.getAllDeviceSongs(this)
        songAdapter.setOnSongClicked(this)
        recyclerView.apply {
            adapter = songAdapter
            hasFixedSize()
        }
    }

    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {

            musicService = (iBinder as MusicService.LocalBinder).instance
            playerAdapter = musicService!!.mediaPlayerHolder
            musicNotificationManager = musicService!!.musicNotificationManager

            if (playbackListener == null) {
                playbackListener = PlaybackListener()
                playerAdapter!!.setPlaybackInfoListener(playbackListener!!)
            }
            if (playerAdapter != null && playerAdapter!!.isPlaying()) {

                restorePlayerStatus()
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
        deviceSongs.addAll(SongProvider.getAllDeviceSongs(this))
        songAdapter.addSongs(deviceSongs)
    }

    private fun updatePlayingInfo(restore: Boolean, startPlay: Boolean) {

        if (startPlay) {
            playerAdapter!!.getMediaPlayer()?.start()
            Handler().postDelayed({
                musicService!!.startForeground(MusicNotificationManager.NOTIFICATION_ID,
                        musicNotificationManager!!.createNotification())
            }, 200)
        }

        val selectedSong = playerAdapter!!.getCurrentSong()

        tvSongTitle?.text = selectedSong?.title
        val duration = selectedSong?.duration
        seekBar?.max = duration!!
        imgSongArtCurrent.setImageBitmap(selectedSong.path?.let { Utils.songArt(it, this@MusicActivity) })

        if (restore) {
            seekBar!!.progress = playerAdapter!!.getPlayerPosition()
            updatePlayingStatus()


            Handler().postDelayed({
                //stop foreground if coming from pause state
                if (musicService!!.isRestoredFromPause) {
                    musicService!!.stopForeground(false)
                    musicService!!.musicNotificationManager!!.notificationManager
                            .notify(MusicNotificationManager.NOTIFICATION_ID,
                                    musicService!!.musicNotificationManager!!.notificationBuilder!!.build())
                    musicService!!.isRestoredFromPause = false
                }
            }, 200)
        }
    }

    private fun updatePlayingStatus() {
        val drawable = if (playerAdapter!!.getState() != PlaybackInfoListener.State.PAUSED)
            R.drawable.ic_pause
        else
            R.drawable.ic_play
        imgBtnPlay!!.post { imgBtnPlay!!.setImageResource(drawable) }
    }

    private fun restorePlayerStatus() {
        seekBar!!.isEnabled = playerAdapter!!.isMediaPlayer()

        //if we are playing and the activity was restarted
        //update the controls panel
        if (playerAdapter != null && playerAdapter!!.isMediaPlayer()) {

            playerAdapter!!.onResumeActivity()
            updatePlayingInfo(true, false)
        }
    }

    private fun doBindService() {
        // Establish a connection with the service.  We use an explicit
        // class name because we want a specific service implementation that
        // we know will be running in our own process (and thus won't be
        // supporting component replacement by other applications).
        bindService(Intent(this,
                MusicService::class.java), mConnection, Context.BIND_AUTO_CREATE)
        isBound = true

        val startNotStickyIntent = Intent(this, MusicService::class.java)
        startService(startNotStickyIntent)
    }

    private fun doUnbindService() {
        if (isBound!!) {
            // Detach our existing connection.
            unbindService(mConnection)
            isBound = false
        }
    }

    private fun onSongSelected(song: Song, songs: List<Song>) {
        if (!seekBar!!.isEnabled) {
            seekBar!!.isEnabled = true
        }
        try {
            playerAdapter!!.setCurrentSong(song, songs)
            playerAdapter!!.initMediaPlayer()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun skipPrev() {
        if (checkIsPlayer()) {
            playerAdapter!!.instantReset()
        }
    }

    private fun resumeOrPause() {
        if (checkIsPlayer()) {
            playerAdapter!!.resumeOrPause()
        } else {
            val songs = SongProvider.getAllDeviceSongs(this)
            if (songs.isNotEmpty()) {
                onSongSelected(songs[0], songs)

            }
        }
    }

    private fun skipNext() {
        if (checkIsPlayer()) {
            playerAdapter!!.skip(true)
        }
    }

    private fun checkIsPlayer(): Boolean {
        return playerAdapter!!.isMediaPlayer()
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
        }
    }

    private fun initSeekBar() {
        seekBar.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    var userSelectedPosition = 0

                    override fun onStartTrackingTouch(seekBar: SeekBar) {
                        userIsSeeking = true
                    }

                    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                        if (fromUser) {
                            userSelectedPosition = progress
                        }
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar) {

                        if (userIsSeeking) {

                        }
                        userIsSeeking = false
                        playerAdapter!!.seekTo(userSelectedPosition)
                    }
                })
    }

    override fun onSongClicked(song: Song) {
        onSongSelected(song, deviceSongs)
    }

    internal inner class PlaybackListener : PlaybackInfoListener() {

        override fun onPositionChanged(position: Int) {
            if (!userIsSeeking) {
                seekBar!!.progress = position
            }
        }

        override fun onStateChanged(@State state: Int) {

            updatePlayingStatus()
            if (playerAdapter!!.getState() != State.PAUSED
                    && playerAdapter!!.getState() != State.PAUSED) {
                updatePlayingInfo(restore = false, startPlay = true)
            }
        }

        override fun onPlaybackCompleted() {
            //After playback is complete
        }
    }
}
