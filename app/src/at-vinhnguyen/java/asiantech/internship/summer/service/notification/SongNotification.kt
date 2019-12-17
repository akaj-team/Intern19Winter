package asiantech.internship.summer.service.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import asiantech.internship.summer.R
import asiantech.internship.summer.service.screen.SoundCloudActivity
import asiantech.internship.summer.service.service.PlayingService
import asiantech.internship.summer.service.utils.SongUtils

class SongNotification(private var context: Context) {
    companion object {
        const val NOTIFICATION_CHANNEL = "sound_cloud"
        const val NOTIFICATION_ID = 100
    }

    private lateinit var notificationManager: NotificationManager
    private lateinit var builder: NotificationCompat.Builder
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var action: NotificationCompat.Action

    public fun getBuilder(): NotificationCompat.Builder {
        return builder
    }

    public fun initNotification() {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationName: CharSequence = context.getString(R.string.channel_name)
            val description = context.getString(R.string.chanel_description)
            val important = NotificationManager.IMPORTANCE_DEFAULT
            notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL, notificationName, important).apply {
                this.description = description
                this.enableLights(true)
                this.lightColor = Color.GREEN
            }
            notificationManager.createNotificationChannel(notificationChannel)
        }
        action = NotificationCompat.Action(R.drawable.ic_pause_black_60dp, context.getString(R.string.pause),
                pendingIntentAction(SongUtils.ACTION_PLAY_AND_PAUSE))
        builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL).setSmallIcon(
                R.drawable.ic_music_note_black_24dp)
                .addAction(R.drawable.ic_skip_previous_black_72dp, context.getString(R.string.previous),
                        pendingIntentAction(SongUtils.ACTION_PREVIOUS))
                .addAction(action)
                .addAction(R.drawable.ic_skip_next_black_72dp, context.getString(R.string.next),
                        pendingIntentAction(SongUtils.ACTION_NEXT))
                .setShowWhen(false)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVisibility(VISIBILITY_PUBLIC)
                .setContentIntent(pendingIntentActivity())
                .setStyle(
                        androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(
                                SongUtils.ActionType.ACTION_PREVIOUS_INT,
                                SongUtils.ActionType.ACTION_PLAY_AND_PAUSE,
                                SongUtils.ActionType.ACTION_NEXT_INT))
    }

    private fun pendingIntentAction(action: String): PendingIntent {
        val intent = Intent(context, PlayingService::class.java)
        intent.action = action
        return PendingIntent.getService(context, 0, intent, 0)
    }

    private fun pendingIntentActivity(): PendingIntent? {
        val intent = Intent(context, SoundCloudActivity::class.java)
        return PendingIntent.getActivity(context, 0, intent, 0)
    }

    fun updatePlayPauseNotification(isPlaying: Boolean) {
        if (isPlaying) {
            action.icon = R.drawable.ic_pause_black_60dp
        } else {
            action.icon = R.drawable.ic_play_arrow_black_60dp
        }
    }
}