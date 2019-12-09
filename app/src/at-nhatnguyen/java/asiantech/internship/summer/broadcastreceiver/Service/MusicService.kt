package asiantech.internship.summer.broadcastreceiver.Service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val uri = intent?.getStringExtra("URI")
        mediaPlayer.setDataSource(this, Uri.parse(uri))
        mediaPlayer.prepare()
        mediaPlayer.start()
        return START_STICKY
    }

    fun pause(){
        mediaPlayer.pause()
    }

    fun start(){
        mediaPlayer.start()
    }


//    override fun onDestroy() {
//        super.onDestroy()
//        mediaPlayer.stop()
//    }
}
