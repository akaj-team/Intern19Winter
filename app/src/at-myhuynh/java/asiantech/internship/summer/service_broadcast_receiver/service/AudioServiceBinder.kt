package asiantech.internship.summer.service_broadcast_receiver.service

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.Handler
import android.os.Message
import java.io.IOException

class AudioServiceBinder : Binder() {
    private var player: MediaPlayer? = null
    var audioFileUri: Uri? = null
    var context: Context? = null
    var audioSeekBarUpdateHandler: Handler? = null

    companion object {
        const val UPDATE_AUDIO_PROGRESS_BAR = 1
    }

    fun startAudio() {
        initAudioPlayer()
        player?.start()
    }

    fun pauseAudio() {
        player?.pause()
    }

    fun stopAudio() {
        player?.let {
            it.stop()
            destroyAudioPlayer()
        }
    }

    private fun initAudioPlayer() {
        try {
            if (player == null) {
                player = MediaPlayer()
                context?.let { audioFileUri?.let { it1 -> player?.setDataSource(it, it1) } }
                player?.prepare()
                val updateAudioProgressThread: Thread = object : Thread() {
                    override fun run() {
                        while (true) {
                            val updateAudioProgressMsg = Message()
                            updateAudioProgressMsg.what = UPDATE_AUDIO_PROGRESS_BAR
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
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    private fun destroyAudioPlayer() {
        player?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
        }
        player = null
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
}
