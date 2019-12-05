package asiantech.internship.winter.musicplayer.playback

import android.media.MediaPlayer
import asiantech.internship.winter.musicplayer.model.Song
import asiantech.internship.winter.musicplayer.playback.PlaybackInfoListener.State

interface PlayerAdapter {

    fun isMediaPlayer(): Boolean

    fun isPlaying(): Boolean

    fun isReset(): Boolean

    fun getCurrentSong(): Song?

    @State
    fun getState(): Int

    fun getPlayerPosition(): Int?

    fun getMediaPlayer(): MediaPlayer?
    fun initMediaPlayer()

    fun release()

    fun resumeOrPause()

    fun reset()

    fun instantReset()

    fun skip(isNext: Boolean)

    fun seekTo(position: Int)

    fun setPlaybackInfoListener(playbackInfoListener: PlaybackInfoListener?)

    fun registerNotificationActionsReceiver(isRegister: Boolean)

    fun setCurrentSong(song: Song, songs: List<Song>)

    fun onPauseActivity()

    fun onResumeActivity()
}
