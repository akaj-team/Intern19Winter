package asiantech.internship.summer.service_broadcast_receiver.service

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.Handler
import android.os.Message
import android.util.Log
import asiantech.internship.summer.service_broadcast_receiver.Song
import java.io.IOException

class AudioServiceBinder : Binder() {
    private var player: MediaPlayer? = null
    var audioFileUri: Uri? = null
    var context: Context? = null
    var songs: MutableList<Song>? = null
    var position = 0
    var audioSeekBarUpdateHandler: Handler? = null
    private var isRunning = false
    private lateinit var updateAudioProgressThread: Thread

    companion object {
        const val UPDATE_AUDIO_PROGRESS_BAR = 1
    }

    fun startAudio() {
        initAudioPlayer()
        player?.start()
    }

    fun pauseAudio() {
        isRunning = false
        player?.pause()
    }

    fun stopAudio() {
        player?.let {
            it.stop()
            destroyAudioPlayer()
        }
        isRunning = false
    }

    fun nextAudio() {
        songs?.let {
            if (position < it.size - 1) {
                position++
            } else {
                position = 0
            }
        }
        stopAudio()
        Log.d("xxx", "Binder - Position - $position")
        songs?.let { audioFileUri = Uri.parse(it[position].path) }
        startAudio()
    }

    fun previousAudio() {
        songs?.let {
            if (position > 0) {
                position--
            } else {
                position = it.size - 1
            }
        }
        stopAudio()
        songs?.let { audioFileUri = Uri.parse(it[position].path) }
        Log.d("xxx", "Binder - Position - $position")
        startAudio()
    }

    private fun initAudioPlayer() {
        Log.d("xxx", "initAudioPlayer")
        try {
            if (player == null) {
                player = MediaPlayer()
                context?.let { audioFileUri?.let { it1 -> player?.setDataSource(it, it1) } }
                player?.prepare()
            }
            isRunning = true
            updateAudioProgressThread = object : Thread() {
                override fun run() {
                    while (isRunning) {
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
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    fun getMediaPlayer() = player

    private fun destroyAudioPlayer() {
        player?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
        }
        player = null
        isRunning = false
    }

    fun currentAudioPosition(): Int {
        var audioPosition = 0
        player?.let { audioPosition = it.currentPosition }
        return audioPosition
    }

    private fun totalAudioDuration(): Int {
        var audioDuration = 0
        player?.let { audioDuration = it.duration }
        return audioDuration
    }

    fun updateCurrentPosition(currentPosition: Int) {
        player?.let {
            it.seekTo(currentPosition * it.duration / 100)
        }
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
