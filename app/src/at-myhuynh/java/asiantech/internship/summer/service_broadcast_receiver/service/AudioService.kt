package asiantech.internship.summer.service_broadcast_receiver.service

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import asiantech.internship.summer.R
import asiantech.internship.summer.service_broadcast_receiver.MusicActivity
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_BACK_NOTIFY
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_NEXT_NOTIFY
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_PAUSE_NOTIFY
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_ART
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_NAME

class AudioService : Service() {

    private val audioServiceBinder = AudioServiceBinder()
    private lateinit var notificationManager: NotificationManager

    companion object {
        private const val NOTIFICATION_ID = 1
    }


    override fun onBind(intent: Intent?): IBinder? {
        return audioServiceBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showNotification(intent)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        notificationManager.cancel(NOTIFICATION_ID)
    }

    private fun showNotification(intent: Intent?) {
        val songName = intent?.getStringExtra(SONG_NAME)
        val songArt = intent?.getStringExtra(SONG_ART)
        val contentIntent = PendingIntent.getActivity(this, 0, Intent(this, MusicActivity::class.java), 0)
        val filter = IntentFilter(ACTION_PAUSE_NOTIFY)
        filter.addAction(ACTION_BACK_NOTIFY)
        filter.addAction(ACTION_NEXT_NOTIFY)
        val pauseAudioIntent = PendingIntent.getBroadcast(this, 0, Intent(ACTION_PAUSE_NOTIFY), PendingIntent.FLAG_UPDATE_CURRENT)
        val backSongIntent = PendingIntent.getBroadcast(this, 0, Intent(ACTION_BACK_NOTIFY), PendingIntent.FLAG_UPDATE_CURRENT)
        val nextSongIntent = PendingIntent.getBroadcast(this, 0, Intent(ACTION_NEXT_NOTIFY), PendingIntent.FLAG_UPDATE_CURRENT)

        val notification =
                NotificationCompat.Builder(this, "")
                        .setContentTitle(songName)
                        .setContentText(songArt)
                        .setAutoCancel(true)
                        .setOngoing(true)
                        .setSmallIcon(R.drawable.ic_play_circle_outline_black_24dp)
                        .addAction(R.drawable.ic_skip_previous_black_24dp, null, backSongIntent)
                        .addAction(R.drawable.ic_play_arrow_black_24dp, null, pauseAudioIntent)
                        .addAction(R.drawable.ic_skip_next_black_24dp, null, nextSongIntent)
                        .setContentIntent(contentIntent).build()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notification.flags = (notification.flags or Notification.FLAG_ONGOING_EVENT)
        notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL

        val notificationReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.action) {
                    ACTION_PAUSE_NOTIFY -> {
                        Log.d("xxx", "notification")
                        audioServiceBinder.getMediaPlayer()?.let {
                            if (it.isPlaying) {
                                Log.d("xxx", "Pause")
                                audioServiceBinder.pauseAudio()
                            } else {
                                audioServiceBinder.startAudio()
                            }
                        }
                    }

                    ACTION_BACK_NOTIFY -> {
                        audioServiceBinder.previousAudio()
                    }

                    ACTION_NEXT_NOTIFY -> {
                        audioServiceBinder.nextAudio()
                    }
                }
            }
        }
        registerReceiver(notificationReceiver, filter)
        startForeground(NOTIFICATION_ID, notification)
    }
}
