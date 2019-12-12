package asiantech.internship.summer.music

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder


class MusicService : Service() {
    private val iBinder = MyBound()
    var mediaPlayer: MediaPlayer? = null
    private var mSong: List<SongModel>? = null
    val isPlaying: Boolean?
        get() = mediaPlayer?.isPlaying

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
        //  mediaPlayer?.setOnPreparedListener { mediaPlayer -> mediaPlayer.start() }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    inner class MyBound() : Binder() {
        val service: MusicService
            get() = this@MusicService
    }

    override fun onDestroy() {
        mediaPlayer?.release()
        super.onDestroy()
    }
}