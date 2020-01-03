package asiantech.internship.winter.musicplayer.ui.music

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
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.winter.musicplayer.Utils
import asiantech.internship.winter.musicplayer.model.Song
import asiantech.internship.winter.musicplayer.network.mockapi.SongMockApi
import asiantech.internship.winter.musicplayer.network.mockapi.SongMockApiResponse
import asiantech.internship.winter.musicplayer.network.webhostapp.SongApi
import asiantech.internship.winter.musicplayer.network.webhostapp.SongResponse
import asiantech.internship.winter.musicplayer.playback.*
import asiantech.internship.winter.musicplayer.ui.online.MusicOnlineActivity
import kotlinx.android.synthetic.`at-trinhnguyen`.activity_music.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicActivity : AppCompatActivity(), View.OnClickListener, SongAdapter.SongClicked {

    private var musicService: MusicService? = null
    private var isBound: Boolean? = null
    private var playerAdapter: PlayerAdapter? = null
    private var userIsSeeking = false
    private var playbackListener: PlaybackListener? = null
    private var deviceSongs = mutableListOf<Song>()
    private var musicNotificationManager: MusicNotificationManager? = null
    private lateinit var songAdapter: SongAdapter
    private var seekBar: SeekBar? = null
    private lateinit var lastSong: Song
    private lateinit var rotate: ObjectAnimator

    companion object {
        private var isOnCreateFirstTime = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        Log.d("aaa", "onCreate")
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
        Log.d("aaa", "onStart")
        playerAdapter?.apply {
            if (isPlaying()) {
                restorePlayerStatus()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("aaa", "onPause")
        doUnbindService()
        playerAdapter?.apply {
            if (isMediaPlayer()) {
                onPauseActivity()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("aaa", "onResume")
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
        imgBtnSearch.setOnClickListener(this)
        view.setOnClickListener(this)
        deviceSongs = SongProvider.getAllDeviceSongs(this)
        songAdapter.setOnSongClicked(this)

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
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET), 1)
        } else getMusic()
    }

    private fun getMusic() {
        deviceSongs.clear()
        deviceSongs.addAll(SongProvider.getAllDeviceSongs(this))
        songAdapter.submitSongs(deviceSongs)
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
            rotate.resume()
        } else {
            drawable = R.drawable.ic_media_play
            rotate.pause()
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
        Log.d("aaa", "onSongSelected")
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
                        Log.d("aaa", "setCurrentSong" + deviceSongs.indexOf(playerAdapter?.getCurrentSong()).toString())
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
            R.id.imgBtnSearch -> {
                //search()
                startActivity(Intent(this, MusicOnlineActivity::class.java))
            }
        }
    }

    val rawString = "Cô Thắm Không Về\n" +
            "Tự Tâm\n" +
            "Từng Yêu\n" +
            "Em Muốn Ta Là Gì\n" +
            "Hongkong 12\n" +
            "Đi Đu Đưa Đi\n" +
            "Kết Thúc Thôi Chuyện Của Mình\n" +
            "Khó Vẽ Nụ Cười\n" +
            "Nghe Nói Anh Sắp Kết Hôn\n" +
            "Xiêu Lòng\n" +
            "Hãy Trao Cho Anh\n" +
            "Ai Đưa Em Về\n" +
            "I'm Still Loving You\n" +
            "Nàng Thơ\n" +
            "So Close\n" +
            "Anh Hiểu Không\n" +
            "Tự Tâm Cover\n" +
            "Mượn Rượu Tỏ Tình\n" +
            "Để Mị Nói Cho Mà Nghe\n" +
            "Cao Ốc 20\n" +
            "Sáng Mắt Chưa\n" +
            "Em Ơi Lên Phố Cover\n" +
            "Say Goodbye\n" +
            "Bỗng Dưng Anh Thành Người Lạ\n" +
            "Có Người\n" +
            "Liệu Giờ\n" +
            "Lạnh Lẽo\n" +
            "Em Đây Chẳng Phải Thúy Kiều\n" +
            "Con Đường Mưa\n" +
            "Cầu Vồng Khuyết\n" +
            "Duyên Âm\n" +
            "Truyền Thái Y\n" +
            "Ta Là Của Nhau\n" +
            "Đừng Chờ Anh Nữa\n" +
            "Tự Dưng Nhớ Anh\n" +
            "Được Không Anh\n" +
            "Lắm Mối Tối Ngồi Không\n" +
            "Kẻ Cắp Gặp Bà Già\n" +
            "Em Thật May Mắn\n" +
            "Trời Giấu Trời Mang Đi Cover\n" +
            "Duyên Nợ Chỉ Là Cái Cớ\n" +
            "Vì Một Người Ra Đi\n" +
            "nàng ther\n" +
            "Dĩ Vãng Phai Màu\n" +
            "Tưởng Chia Tay Sẽ Vui\n" +
            "Kẽo Cà Kẽo Kẹt\n" +
            "Cố Nhớ Để Mà Quên\n" +
            "Níu Ân Tình\n" +
            "Nhạt\n" +
            "Yêu Lại Từ Đầu\n" +
            "Mãi Mãi Bên Nhau\n" +
            "1 Phút\n" +
            "Những Kẻ Mộng Mơ\n" +
            "Tình Khúc Vàng\n" +
            "An Tâm Đi Đầu Thai\n" +
            "Dối Lòng\n" +
            "Chẳng Một Ai Thấy\n" +
            "Rực Rỡ Tháng Năm (Tháng Năm Rực Rỡ OST)\n" +
            "Chờ Ngày Anh Nhận Ra Em (Mối Tình Đầu Của Tôi OST)\n" +
            "Tôi Không Tin\n" +
            "Lần Đầu\n" +
            "Anh Chẳng Sao Mà\n" +
            "Điều Ngọt Ngào Nhất\n" +
            "Dĩ Nhiên Em Không Ở Đây\n" +
            "Đi Về Nơi Xa\n" +
            "Tình Yêu Lung Linh\n" +
            "Anh Đưa Em Đi\n" +
            "Hành Lang Cũ (Hạ Nhớ) Cover\n" +
            "Người Ta Nói (Ballad Version 2017)\n" +
            "Nuối Tiếc (Phúc Acoustic 1)\n" +
            "Nếu Anh Là\n" +
            "Thương Em Là Điều Anh Không Thể Ngờ\n" +
            "Em Đừng Hỏi\n" +
            "Mình Xa Mình Yêu\n" +
            "Đến Sau Một Người\n" +
            "Tình Đơn Phương\n" +
            "Chúng Ta Không Thuộc Về Nhau\n" +
            "Sai Người Sai Thời Điểm\n" +
            "Chạy Ngay Đi\n" +
            "Hẹn Một Mai\n" +
            "Cầu Hôn\n" +
            "Xin Cho Mãi Yêu\n" +
            "Tim Anh Thắt Lại\n" +
            "Thật Lòng\n" +
            "Trưởng Thành\n" +
            "Liệu Anh Có Thể Yêu Em\n" +
            "Chúng Ta Dừng Lại Ở Đây Thôi\n" +
            "Nơi Em Muốn Tới\n" +
            "Xa Một Trời Thương Nhớ\n" +
            "Hết Yêu Thì Em Cứ Đi\n" +
            "Điều Không Đơn Giản\n" +
            "Gánh Mẹ\n" +
            "Chúng Ta Chẳng Giống Nhau\n" +
            "Xứng Đôi Cưới Thôi \n" +
            "Người Ta Thành Đôi Hết Rồi\n" +
            "Bùa Yêu\n" +
            "Điều Gì Đến Sẽ Đến\n" +
            "Lửng Lơ"

    private val songNames: List<String> = rawString.split("\n")

    private fun search() {
        Toast.makeText(this, edtSearch.text, Toast.LENGTH_SHORT).show()

        songNames.forEach {
            SongApi.songApiService.getSong(it)
                    .enqueue(object : Callback<SongResponse> {
                        override fun onFailure(call: Call<SongResponse>, t: Throwable) {
                            Log.d("bbb", "fail")
                            call.cancel()
                        }

                        override fun onResponse(call: Call<SongResponse>, response: Response<SongResponse>) {

                            response.body()?.let { songResponse ->
                                //Toast.makeText(applicationContext, songProperty.messages?.get(0)?.attachment?.payload?.url, Toast.LENGTH_SHORT).show()
                                Log.d("bbb", songResponse.messages.toString())
                                val a: List<String> = songResponse.messages?.get(0)?.text.toString().split("Bài ")
                                val title: List<String> = a[1].split(" của ca sĩ ")
                                val artist: List<String> = title[1].split(" phải không?")

                                SongMockApiResponse(
                                        "",
                                        title[0],
                                        artist[0],
                                        songResponse.messages?.get(1)?.attachment?.payload?.url.toString(),
                                        songResponse.messages?.get(1)?.attachment?.payload?.url.toString()
                                ).let { songMockApiResponse ->
                                    SongMockApi.songMockApiService.postSong(songMockApiResponse)
                                            .enqueue(object : Callback<SongMockApiResponse> {
                                                override fun onFailure(call: Call<SongMockApiResponse>, t: Throwable) {
                                                    Log.d("bbb", t.toString())
                                                    call.cancel()
                                                }

                                                override fun onResponse(call: Call<SongMockApiResponse>, response: Response<SongMockApiResponse>) {
                                                    response.body()?.let { songResponse ->
                                                        Log.d("bbb", songResponse.toString())
                                                    }

                                                }
                                            })
                                }
                            }
                        }
                    })
        }
    }

    private fun restoreLastSongPlay() {
        getSharedPreferences(getString(R.string.shared_prefs_file_name), Context.MODE_PRIVATE)?.apply {
            val songIndex = getInt(getString(R.string.shared_prefs_song_index), 0)
            Log.d("aaa", "song index $songIndex")
            recyclerView.postDelayed({
                try {
                    onSongSelected(deviceSongs[songIndex], deviceSongs)
                } catch (e: IndexOutOfBoundsException) {
                    e.printStackTrace()
                }
                (recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(songIndex, 160)
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
                            Log.d("aaa", userSelectedPosition.toString())
                        }
                        tvDurationLeft.text = Utils.formatDuration(progress)
                        tvDurationRight.text = playerAdapter?.getCurrentSong()?.duration?.let { duration ->
                            Utils.formatDuration(duration - progress)
                        }
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar) {
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
