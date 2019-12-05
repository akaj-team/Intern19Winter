package asiantech.internship.summer.service_broadcast_receiver.service

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.Handler
import android.os.Message
import java.io.IOException

class AudioServiceBinder : Binder() {
    private var audioPlayer: MediaPlayer? = null
    var audioFileUri: Uri? = null
    var context: Context? = null
    var audioSeekBarUpdateHandler: Handler? = null

    companion object {
        const val UPDATE_AUDIO_PROGRESS_BAR = 1
    }

    fun startAudio() {
        initAudioPlayer()
        audioPlayer?.start()
    }

    fun pauseAudio() {
        audioPlayer?.pause()
    }

    fun stopAudio() {
        audioPlayer?.let {
            it.start()
            destroyAudioPlayer()
        }
    }

    private fun initAudioPlayer() {
        try {
            if (audioPlayer == null) {
                audioPlayer = MediaPlayer()
                context?.let { audioFileUri?.let { it1 -> audioPlayer?.setDataSource(it, it1) } }
                audioPlayer?.prepare()
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
        audioPlayer?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
        }
        audioPlayer = null
    }

    private fun currentAudioPosition(): Int {
        var audioPosition = 0
        audioPlayer?.let { audioPosition = it.currentPosition }
        return audioPosition
    }

    private fun totalAudioDuration(): Int {
        var audioDuration = 0
        audioPlayer?.let { audioDuration = it.duration }
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
