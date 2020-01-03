package asiantech.internship.winter.musicplayer.playback

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.session.MediaSessionManager
import android.os.Build
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import asiantech.internship.summer.R
import asiantech.internship.winter.musicplayer.Utils
import asiantech.internship.winter.musicplayer.model.Song
import asiantech.internship.winter.musicplayer.ui.music.MusicActivity

class MusicNotificationManager internal constructor(private val musicService: MusicService) {

    val notificationManager: NotificationManager = musicService.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    var notificationBuilder: NotificationCompat.Builder? = null
        private set
    private var mediaSession: MediaSessionCompat? = null
    private var mediaSessionManager: MediaSessionManager? = null
    private var transportControls: MediaControllerCompat.TransportControls? = null
    private val context: Context = musicService.baseContext

    private fun playerAction(action: String): PendingIntent {
        val pauseIntent = Intent()
        pauseIntent.action = action
        return PendingIntent.getBroadcast(musicService, REQUEST_CODE, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    fun createNotification(): Notification? {
        val song = musicService.mediaPlayerHolder?.getCurrentSong()

        notificationBuilder = NotificationCompat.Builder(musicService, CHANNEL_ID)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        val openPlayerIntent = Intent(musicService, MusicActivity::class.java)
        openPlayerIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val contentIntent = PendingIntent.getActivity(musicService, REQUEST_CODE,
                openPlayerIntent, 0)

        val artist = song?.artistName
        val songTitle = song?.title

        song?.let { initMediaSession(it) }

        notificationBuilder?.apply {
            setShowWhen(false)
            setSmallIcon(R.drawable.ic_music_player)
            setLargeIcon(song?.path?.let { Utils.songArt(it, musicService.baseContext) })
            color = ContextCompat.getColor(context, R.color.colorAccent)
            setContentTitle(songTitle)
            setContentText(artist)
            setContentIntent(contentIntent)
            addAction(notificationAction(PREV_ACTION))
            addAction(notificationAction(PLAY_PAUSE_ACTION))
            addAction(notificationAction(NEXT_ACTION))
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        }
        notificationBuilder?.setStyle(androidx.media.app.NotificationCompat.MediaStyle()
                .setMediaSession(mediaSession?.sessionToken)
                .setShowActionsInCompactView(0, 1, 2))
        return notificationBuilder?.build()
    }

    private fun notificationAction(action: String): NotificationCompat.Action {

        val icon: Int = when (action) {
            PREV_ACTION -> R.drawable.ic_media_prev
            PLAY_PAUSE_ACTION ->
                if (musicService.mediaPlayerHolder?.getState() != PlaybackInfoListener.State.PAUSED)
                    R.drawable.ic_media_pause
                else
                    R.drawable.ic_media_play
            NEXT_ACTION -> R.drawable.ic_media_next
            else -> R.drawable.ic_media_prev
        }
        return NotificationCompat.Action.Builder(icon, action, playerAction(action)).build()
    }

    @RequiresApi(26)
    private fun createNotificationChannel() {

        if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
            val notificationChannel = NotificationChannel(CHANNEL_ID,
                    musicService.getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_LOW)

            notificationChannel.description = musicService.getString(R.string.app_name)

            notificationChannel.enableLights(false)
            notificationChannel.enableVibration(false)
            notificationChannel.setShowBadge(false)

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun initMediaSession(song: Song) {
        mediaSessionManager = context.getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager
        mediaSession = MediaSessionCompat(context, "AudioPlayer")
        transportControls = mediaSession?.controller?.transportControls
        mediaSession?.isActive = true
        mediaSession?.setFlags(MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS)
        updateMetaData(song)
    }

    private fun updateMetaData(song: Song) {
        mediaSession?.setMetadata(MediaMetadataCompat.Builder()
                .putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, song.path?.let { Utils.songArt(it, context) })
                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, song.artistName)
                .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, song.albumName)
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, song.title)
                .build())
    }

    companion object {
        const val NOTIFICATION_ID = 101
        internal const val PLAY_PAUSE_ACTION = "action.PLAYPAUSE"
        internal const val NEXT_ACTION = "action.NEXT"
        internal const val PREV_ACTION = "action.PREV"
        private const val CHANNEL_ID = "action.CHANNEL_ID"
        private const val REQUEST_CODE = 100
    }
}
