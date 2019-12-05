package asiantech.internship.winter.musicplayer.playback

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.PowerManager
import asiantech.internship.winter.musicplayer.model.Song
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class MediaPlayerHolder(private val musicService: MusicService?) :
        PlayerAdapter, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {
    private val context: Context? = musicService?.applicationContext
    private val audioManager: AudioManager
    private var mediaPlayer: MediaPlayer? = null
    private var playbackInfoListener: PlaybackInfoListener? = null
    private var executor: ScheduledExecutorService? = null
    private var seekBarPositionUpdateTask: Runnable? = null
    private var selectedSong: Song? = null
    private var songs: List<Song>? = null
    private var isReplaySong = false
    @PlaybackInfoListener.State
    private var state: Int = 0
    private var notificationActionsReceiver: NotificationReceiver? = null
    private var musicNotificationManager: MusicNotificationManager? = null
    private var currentAudioFocusState = AUDIO_NO_FOCUS_NO_DUCK
    private var playOnFocusGain: Boolean = false
    private val onAudioFocusChangeListener = AudioManager.OnAudioFocusChangeListener { focusChange ->
        when (focusChange) {
            AudioManager.AUDIOFOCUS_GAIN -> currentAudioFocusState = AUDIO_FOCUSED
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ->
                // Audio focus was lost, but it's possible to duck (i.e.: play quietly)
                currentAudioFocusState = AUDIO_NO_FOCUS_CAN_DUCK
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                // Lost audio focus, but will gain it back (shortly), so note whether
                // playback should resume
                currentAudioFocusState = AUDIO_NO_FOCUS_NO_DUCK
                playOnFocusGain = isMediaPlayer() && state == PlaybackInfoListener.State.PLAYING || state == PlaybackInfoListener.State.RESUMED
            }
            AudioManager.AUDIOFOCUS_LOSS ->
                // Lost audio focus, probably "permanently"
                currentAudioFocusState = AUDIO_NO_FOCUS_NO_DUCK
        }

        if (mediaPlayer != null) {
            // Update the player state based on the change
            configurePlayerState()
        }
    }

    init {
        audioManager = context?.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }

    private fun registerActionsReceiver() {
        notificationActionsReceiver = NotificationReceiver()
        val intentFilter = IntentFilter()

        intentFilter.addAction(MusicNotificationManager.PREV_ACTION)
        intentFilter.addAction(MusicNotificationManager.PLAY_PAUSE_ACTION)
        intentFilter.addAction(MusicNotificationManager.NEXT_ACTION)
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG)
        intentFilter.addAction(AudioManager.ACTION_AUDIO_BECOMING_NOISY)

        musicService?.registerReceiver(notificationActionsReceiver, intentFilter)
    }

    private fun unregisterActionsReceiver() {
        if (musicService != null && notificationActionsReceiver != null) {
            try {
                musicService.unregisterReceiver(notificationActionsReceiver)
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }

        }
    }

    override fun registerNotificationActionsReceiver(isRegister: Boolean) {

        if (isRegister) {
            registerActionsReceiver()
        } else {
            unregisterActionsReceiver()
        }
    }

    override fun getCurrentSong(): Song? {
        return selectedSong
    }

    override fun setCurrentSong(song: Song, songs: List<Song>) {
        selectedSong = song
        this.songs = songs
    }

    override fun onCompletion(mediaPlayer: MediaPlayer) {
        if (playbackInfoListener != null) {
            playbackInfoListener?.onStateChanged(PlaybackInfoListener.State.COMPLETED)
            playbackInfoListener?.onPlaybackCompleted()
        }

        if (isReplaySong) {
            if (isMediaPlayer()) {
                resetSong()
            }
            isReplaySong = false
        } else {
            skip(true)
        }
    }

    override fun onResumeActivity() {
        startUpdatingCallbackWithPosition()
    }

    override fun onPauseActivity() {
        stopUpdatingCallbackWithPosition()
    }

    private fun tryToGetAudioFocus() {

        val result = audioManager.requestAudioFocus(
                onAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN)
        currentAudioFocusState = if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            AUDIO_FOCUSED
        } else {
            AUDIO_NO_FOCUS_NO_DUCK
        }
    }

    private fun giveUpAudioFocus() {
        if (audioManager.abandonAudioFocus(onAudioFocusChangeListener) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            currentAudioFocusState = AUDIO_NO_FOCUS_NO_DUCK
        }
    }

    override fun setPlaybackInfoListener(playbackInfoListener: PlaybackInfoListener?) {
        this.playbackInfoListener = playbackInfoListener
    }

    private fun setStatus(@PlaybackInfoListener.State state: Int) {

        this.state = state
        if (playbackInfoListener != null) {
            playbackInfoListener?.onStateChanged(state)
        }
    }

    private fun resumeMediaPlayer() {
        if (!isPlaying()) {
            mediaPlayer?.start()
            setStatus(PlaybackInfoListener.State.RESUMED)
            musicService?.startForeground(MusicNotificationManager.NOTIFICATION_ID, musicNotificationManager?.createNotification())
        }
    }

    private fun pauseMediaPlayer() {
        setStatus(PlaybackInfoListener.State.PAUSED)
        mediaPlayer?.pause()
        musicService?.stopForeground(false)
        musicNotificationManager?.notificationManager?.notify(MusicNotificationManager.NOTIFICATION_ID, musicNotificationManager?.createNotification())
    }

    private fun resetSong() {
        mediaPlayer?.seekTo(0)
        mediaPlayer?.start()
        setStatus(PlaybackInfoListener.State.PLAYING)
    }

    /**
     * Syncs the mMediaPlayer position with mPlaybackProgressCallback via recurring task.
     */
    private fun startUpdatingCallbackWithPosition() {
        if (executor == null) {
            executor = Executors.newSingleThreadScheduledExecutor()
        }
        if (seekBarPositionUpdateTask == null) {
            seekBarPositionUpdateTask = Runnable { updateProgressCallbackTask() }
        }

        executor?.scheduleAtFixedRate(
                seekBarPositionUpdateTask,
                0,
                1000,
                TimeUnit.MILLISECONDS
        )
    }

    // Reports media playback position to mPlaybackProgressCallback.
    private fun stopUpdatingCallbackWithPosition() {
        if (executor != null) {
            executor?.shutdownNow()
            executor = null
            seekBarPositionUpdateTask = null
        }
    }

    private fun updateProgressCallbackTask() {
        mediaPlayer?.let { mediaPlayer ->
            if (isMediaPlayer() && mediaPlayer.isPlaying) {
                val currentPosition = mediaPlayer.currentPosition
                if (playbackInfoListener != null) {
                    currentPosition.let { playbackInfoListener?.onPositionChanged(it) }
                }
            }
        }
    }

    override fun instantReset() {
        if (isMediaPlayer()) {
            mediaPlayer?.let { mediaPlayer ->
                if (mediaPlayer.currentPosition < 5000) {
                    skip(false)
                } else {
                    resetSong()
                }
            }
        }
    }

    override fun initMediaPlayer() {

        try {
            if (mediaPlayer != null) {
                mediaPlayer?.reset()
            } else {
                mediaPlayer = MediaPlayer()

                mediaPlayer?.setOnPreparedListener(this)
                mediaPlayer?.setOnCompletionListener(this)
                mediaPlayer?.setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK)
                mediaPlayer?.setAudioAttributes(AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build())
                musicNotificationManager = musicService?.musicNotificationManager
            }
            tryToGetAudioFocus()
            mediaPlayer?.setDataSource(selectedSong?.path)
            mediaPlayer?.prepare()
        } catch (e: Exception) {
            e.printStackTrace()
            skip(true)
        }

    }


    override fun getMediaPlayer(): MediaPlayer? {
        return mediaPlayer
    }

    override fun onPrepared(mediaPlayer: MediaPlayer) {

        startUpdatingCallbackWithPosition()
        setStatus(PlaybackInfoListener.State.PLAYING)
    }


    override fun release() {
        if (isMediaPlayer()) {
            mediaPlayer?.release()
            mediaPlayer = null
            giveUpAudioFocus()
            unregisterActionsReceiver()
        }
    }

    override fun isPlaying(): Boolean {
        return isMediaPlayer() && requireNotNull(mediaPlayer).isPlaying
    }

    override fun resumeOrPause() {

        if (isPlaying()) {
            pauseMediaPlayer()
        } else {
            resumeMediaPlayer()
        }
    }

    @PlaybackInfoListener.State
    override fun getState(): Int {
        return state
    }

    override fun isMediaPlayer(): Boolean {
        return mediaPlayer != null
    }

    override fun reset() {
        isReplaySong = !isReplaySong
    }

    override fun isReset(): Boolean {
        return isReplaySong
    }

    override fun skip(isNext: Boolean) {
        getSkipSong(isNext)
    }

    private fun getSkipSong(isNext: Boolean) {
        songs?.let { songs ->
            val currentIndex = songs.indexOf(selectedSong)
            val index: Int

            try {
                index = if (isNext) {
                    currentIndex + 1
                } else {
                    currentIndex - 1
                }
                selectedSong = songs[index]
            } catch (e: IndexOutOfBoundsException) {
                selectedSong = if (currentIndex != 0) songs[0] else songs[songs.size - 1]
                e.printStackTrace()
            }
        }
        initMediaPlayer()
    }

    override fun seekTo(position: Int) {
        if (isMediaPlayer()) {
            mediaPlayer?.seekTo(position)
        }
    }

    override fun getPlayerPosition(): Int? {
        return mediaPlayer?.currentPosition
    }

    /**
     * Reconfigures the player according to audio focus settings and starts/restarts it. This method
     * starts/restarts the MediaPlayer instance respecting the current audio focus state. So if we
     * have focus, it will play normally; if we don't have focus, it will either leave the player
     * paused or set it to a low volume, depending on what is permitted by the current focus
     * settings.
     */
    private fun configurePlayerState() {

        if (currentAudioFocusState == AUDIO_NO_FOCUS_NO_DUCK) {
            // We don't have audio focus and can't duck, so we have to pause
            pauseMediaPlayer()
        } else {

            if (currentAudioFocusState == AUDIO_NO_FOCUS_CAN_DUCK) {
                // We're permitted to play, but only if we 'duck', ie: play softly
                mediaPlayer?.setVolume(VOLUME_DUCK, VOLUME_DUCK)
            } else {
                mediaPlayer?.setVolume(VOLUME_NORMAL, VOLUME_NORMAL)
            }

            // If we were playing when we lost focus, we need to resume playing.
            if (playOnFocusGain) {
                resumeMediaPlayer()
                playOnFocusGain = false
            }
        }
    }

    private inner class NotificationReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {

            val action = intent.action

            if (action != null) {

                when (action) {
                    MusicNotificationManager.PREV_ACTION -> instantReset()
                    MusicNotificationManager.PLAY_PAUSE_ACTION -> resumeOrPause()
                    MusicNotificationManager.NEXT_ACTION -> skip(true)

                    BluetoothDevice.ACTION_ACL_DISCONNECTED -> if (selectedSong != null) {
                        pauseMediaPlayer()
                    }
                    BluetoothDevice.ACTION_ACL_CONNECTED -> if (selectedSong != null && !isPlaying()) {
                        resumeMediaPlayer()
                    }
                    Intent.ACTION_HEADSET_PLUG -> if (selectedSong != null) {
                        when (intent.getIntExtra("state", -1)) {
                            //0 means disconnected
                            0 -> pauseMediaPlayer()
                            //1 means connected
                            1 -> if (!isPlaying()) {
                                resumeMediaPlayer()
                            }
                        }
                    }
                    AudioManager.ACTION_AUDIO_BECOMING_NOISY -> if (isPlaying()) {
                        pauseMediaPlayer()
                    }
                }
            }
        }
    }

    companion object {

        // The volume we set the media player to when we lose audio focus, but are
        // allowed to reduce the volume instead of stopping playback.
        private const val VOLUME_DUCK = 0.2f
        // The volume we set the media player when we have audio focus.
        private const val VOLUME_NORMAL = 1.0f
        // we don't have audio focus, and can't duck (play at a low volume)
        private const val AUDIO_NO_FOCUS_NO_DUCK = 0
        // we don't have focus, but can duck (play at a low volume)
        private const val AUDIO_NO_FOCUS_CAN_DUCK = 1
        // we have full audio focus
        private const val AUDIO_FOCUSED = 2
    }
}
