package asiantech.internship.summer.service_broadcast_receiver.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class AudioService : Service() {

    private val audioServiceBinder = AudioServiceBinder()
    private lateinit var player: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return audioServiceBinder
    }

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer()
    }

}
