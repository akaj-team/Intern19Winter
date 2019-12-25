package asiantech.internship.summer.service_broadcast_receiver.service

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.Handler
import android.os.Message
import asiantech.internship.summer.service_broadcast_receiver.Song
import asiantech.internship.summer.service_broadcast_receiver.Utils
import java.io.IOException
import kotlin.random.Random

class AudioServiceBinder : Binder() {
    private lateinit var updateAudioProgressThread: Thread
    var audioSeekBarUpdateHandler: Handler? = null
    private var player: MediaPlayer? = null
    var songs: MutableList<Song>? = null
    var audioFileUri: Uri? = null
    private var isRunning = false
    var context: Context? = null
    var position = 0
    private var isShuffle = false
    private var repeat = 0
    private val songsPlayed = mutableListOf<Int>()

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
        context?.let { isShuffle = Utils.readIsShuffle(it) }
        songs?.let {
            if (isShuffle) {
                songs?.let {
                    while (true) {
                        val nextPosition = Random.nextInt(0, it.size)
                        if (!isExist(nextPosition)) {
                            songsPlayed.add(nextPosition)
                            position = nextPosition
                            break
                        }
                    }
                }
            } else {
                songs?.let {
                    if (position < it.size - 1) {
                        position++
                    } else {
                        position = 0
                    }
                }
            }
        }
        context?.let {
            Utils.writePositionPreferences(it, position)
        }
        player?.let {
            if (it.isPlaying) {
                stopAudio()
                songs?.let { it1 -> audioFileUri = Uri.parse(it1[position].path) }
                startAudio()
            }
        }
    }

    fun previousAudio() {
        context?.let { isShuffle = Utils.readIsShuffle(it) }
        songs?.let {
            if (isShuffle) {
                while (true) {
                    val nextPosition = Random.nextInt(0, it.size)
                    if (!isExist(nextPosition)) {
                        songsPlayed.add(nextPosition)
                        position = nextPosition
                        break
                    }
                }
            } else {
                if (position > 0) {
                    position--
                } else {
                    position = it.size - 1
                }
            }
        }
        context?.let { Utils.writePositionPreferences(it, position) }
        player?.let {
            if (it.isPlaying) {
                stopAudio()
                songs?.let { it1 -> audioFileUri = Uri.parse(it1[position].path) }
                startAudio()
            }
        }
    }

    private fun initAudioPlayer() {
        context?.let {
            isShuffle = Utils.readIsShuffle(it)
            repeat = Utils.readAudioRepeat(it)
        }
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

    private fun isExist(position: Int): Boolean {
        songs?.let {
            if (it.size == songsPlayed.size) {
                songsPlayed.clear()
            }
        }
        return songsPlayed.contains(position)
    }
}
