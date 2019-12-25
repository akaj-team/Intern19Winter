package asiantech.internship.summer.service_broadcast_receiver.service

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import asiantech.internship.summer.R
import asiantech.internship.summer.service_broadcast_receiver.MusicActivity
import asiantech.internship.summer.service_broadcast_receiver.Utils
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_BACK_NOTIFY
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_CLEAR_NOTIFY
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_NEXT_NOTIFY
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_PAUSE_NOTIFY
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_ART
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_NAME
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_URI

class AudioService : Service() {

    private val audioServiceBinder = AudioServiceBinder()
    private var notificationManager: NotificationManager? = null
    private lateinit var remoteViews: RemoteViews

    companion object {
        private const val NOTIFICATION_ID = 1
    }

    override fun onBind(intent: Intent?): IBinder? {
        return audioServiceBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        notification(intent)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        notificationManager?.cancel(NOTIFICATION_ID)
        unregisterReceiver(notificationReceiver)
    }

    private val notificationReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("xxx", "notificationReceiver")
            when (intent?.action) {
                ACTION_NEXT_NOTIFY -> {
                    audioServiceBinder.nextAudio()

                }

                ACTION_BACK_NOTIFY -> {
                    audioServiceBinder.previousAudio()
                }

                ACTION_PAUSE_NOTIFY -> {
                    audioServiceBinder.getMediaPlayer()?.let {
                        if (it.isPlaying) {
                            audioServiceBinder.pauseAudio()
                            remoteViews.setImageViewResource(R.id.btnPlay, R.drawable.ic_play_arrow_black_24dp)
                        } else {
                            remoteViews.setImageViewResource(R.id.btnPlay, R.drawable.ic_pause_black_48dp)
                            audioServiceBinder.startAudio()
                        }
                    }
                }

                ACTION_CLEAR_NOTIFY -> {
                    Log.d("xxx", " Close service")
                    audioServiceBinder.stopAudio()
                    stopSelf()
                }
            }
        }
    }

    private fun notification(intent: Intent?) {
        val songName = intent?.getStringExtra(SONG_NAME)
        val songArt = intent?.getStringExtra(SONG_ART)
        val songUri = intent?.getStringExtra(SONG_URI)
        val bitmap = Utils.getCoverPicture(this, Uri.parse(songUri))
        remoteViews = RemoteViews(packageName, R.layout.notification_custom)

        val filter = IntentFilter(ACTION_PAUSE_NOTIFY)
        filter.addAction(ACTION_BACK_NOTIFY)
        filter.addAction(ACTION_NEXT_NOTIFY)
        filter.addAction(ACTION_CLEAR_NOTIFY)

        val contentIntent = PendingIntent.getActivity(this, 0, Intent(this, MusicActivity::class.java), 0)
        val pauseAudioIntent = PendingIntent.getBroadcast(this, 0, Intent(ACTION_PAUSE_NOTIFY), PendingIntent.FLAG_UPDATE_CURRENT)
        val backSongIntent = PendingIntent.getBroadcast(this, 0, Intent(ACTION_BACK_NOTIFY), PendingIntent.FLAG_UPDATE_CURRENT)
        val nextSongIntent = PendingIntent.getBroadcast(this, 0, Intent(ACTION_NEXT_NOTIFY), PendingIntent.FLAG_UPDATE_CURRENT)
        val closeSongIntent = PendingIntent.getBroadcast(this, 0, Intent(ACTION_CLEAR_NOTIFY), PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(applicationContext, "")
                .setContent(remoteViews)
                .setTicker(songName)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_pause_circle_outline_black_24dp)

        remoteViews.setTextViewText(R.id.tvSongName, songName)
        remoteViews.setTextViewText(R.id.tvSongArtist, songArt)
        bitmap?.let {
            remoteViews.setImageViewBitmap(R.id.imgSong, it)
        }
        remoteViews.setOnClickPendingIntent(R.id.btnClear, closeSongIntent)
        remoteViews.setOnClickPendingIntent(R.id.btnPlay, pauseAudioIntent)
        remoteViews.setOnClickPendingIntent(R.id.btnNext, nextSongIntent)
        remoteViews.setOnClickPendingIntent(R.id.btnPrevious, backSongIntent)
        val notify: Notification = builder.build()
        notify.contentIntent = contentIntent
        registerReceiver(notificationReceiver, filter)
        startForeground(NOTIFICATION_ID, notify)
    }
}
