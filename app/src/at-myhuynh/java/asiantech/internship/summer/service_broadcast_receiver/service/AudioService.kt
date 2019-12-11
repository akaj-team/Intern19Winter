package asiantech.internship.summer.service_broadcast_receiver.service

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.util.Log
import androidx.core.app.NotificationCompat
import asiantech.internship.summer.R
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_PAUSE
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_PLAY
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_START
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_ART
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_NAME
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_URI
import asiantech.internship.summer.service_broadcast_receiver.fragment.ListSongFragment

class AudioService : Service() {

    private val audioServiceBinder = AudioServiceBinder()
    private var player: MediaPlayer? = null
    private lateinit var notificationManager: NotificationManager
    var audioSeekBarUpdateHandler: Handler? = null
    private var audioPosition = 0

    companion object {
        private const val NOTIFICATION_ID = 1
    }

    override fun onBind(intent: Intent?): IBinder? {
        return audioServiceBinder
    }

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer()
        player?.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("xxx", "action ===> ${intent?.action}")
        showNotification(intent)
        when (intent?.action) {
            ACTION_PAUSE -> {
                Log.d("xxx", ACTION_PAUSE)
                pauseProcess()
            }

            ACTION_PLAY -> {
                Log.d("xxx", ACTION_PLAY)
                playProcess()
            }

            ACTION_START -> {
                initAudioPlayer(intent)
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        notificationManager.cancel(NOTIFICATION_ID)
        player?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
        }
        player = null
        audioSeekBarUpdateHandler = null
        audioPosition = 0
    }

    private fun pauseProcess() {
        player?.pause()
        audioPosition = currentAudioPosition()
    }

    private fun playProcess() {
        player?.seekTo(audioPosition)
        player?.start()
    }

    private fun initAudioPlayer(intent: Intent?) {
        val audioUri = intent?.getStringExtra(SONG_URI)
        player?.let {
            it.setDataSource(this, Uri.parse(audioUri))
            it.prepare()
            it.start()
        }
        val updateAudioProgressThread: Thread = object : Thread() {
            override fun run() {
                while (true) {
                    val updateAudioProgressMsg = Message()
                    updateAudioProgressMsg.what = AudioServiceBinder.UPDATE_AUDIO_PROGRESS_BAR
                    audioSeekBarUpdateHandler?.sendMessage(updateAudioProgressMsg)
                    try {
                        sleep(1000)
                    } catch (ex: InterruptedException) {
                        ex.printStackTrace()
                    }
                }
            }
        }
        updateAudioProgressThread.start()
    }

    private fun currentAudioPosition(): Int {
        var audioPosition = 0
        player?.let { audioPosition = it.currentPosition }
        return audioPosition
    }

    private fun totalAudioDuration(): Int {
        var audioDuration = 0
        player?.let { audioDuration = it.duration }
        return audioDuration
    }

    fun audioProgress(): Int {
        var progress = 0
        val currAudioPosition = currentAudioPosition()
        val totalAudioDuration = totalAudioDuration()
        if (totalAudioDuration > 0) {
            progress = currAudioPosition * 100 / totalAudioDuration
        }
        return progress
    }

    private fun showNotification(intent: Intent?) {
        val songName = intent?.getStringExtra(SONG_NAME)
        val songArt = intent?.getStringExtra(SONG_ART)
        val contentIntent = PendingIntent.getActivity(this, 0, Intent(this, ListSongFragment::class.java), 0)
        val notification =
                NotificationCompat.Builder(this, "")
                        .setContentTitle(songName)
                        .setContentText(songArt)
                        .setSmallIcon(R.drawable.ic_play_circle_outline_black_24dp)
                        .addAction(R.drawable.ic_skip_previous_black_24dp, null, contentIntent)
                        .addAction(R.drawable.ic_play_arrow_black_24dp, null, contentIntent)
                        .addAction(R.drawable.ic_skip_next_black_24dp, null, contentIntent)
                        .setContentIntent(contentIntent).build()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notification.flags = (notification.flags or Notification.FLAG_ONGOING_EVENT)
        notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL

        notificationManager.notify(NOTIFICATION_ID, notification)
    }
}
