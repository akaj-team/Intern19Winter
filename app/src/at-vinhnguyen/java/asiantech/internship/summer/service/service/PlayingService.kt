package asiantech.internship.summer.service.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.util.Log
import asiantech.internship.summer.service.model.Song
import asiantech.internship.summer.service.utils.SongUtils.DEFAULT_SONG_POSITION
import asiantech.internship.summer.service.utils.SongUtils.EXTRA_SONGS
import asiantech.internship.summer.service.utils.SongUtils.EXTRA_SONG_POSITION
import asiantech.internship.summer.service.utils.SongUtils.LoopType


class PlayingService : Service(), MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private lateinit var songs: ArrayList<Song>
    private lateinit var mediaPlayer: MediaPlayer
    private var currentSongPosition = 0
    private var loopType = LoopType.LOOP_ALL
    private lateinit var songBinder: SongBinder

    companion object {
        fun getPlayingServiceIntent(context: Context, songs: ArrayList<Song>, currentSongPosition: Int): Intent {
            val playingServiceIntent = Intent(context, PlayingService::class.java)
            playingServiceIntent.putParcelableArrayListExtra(EXTRA_SONGS, songs)
            playingServiceIntent.putExtra(EXTRA_SONG_POSITION, currentSongPosition)
            return playingServiceIntent
        }

    }

    private fun initMediaPlayer() {
        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioAttributes(AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build())
        mediaPlayer.setOnPreparedListener(this)
    }

    override fun onCreate() {
        super.onCreate()
        songBinder = SongBinder()
        initMediaPlayer()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return songBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.apply {
            songs = getParcelableArrayListExtra<Song>(EXTRA_SONGS) as ArrayList<Song>
            currentSongPosition = getIntExtra(EXTRA_SONG_POSITION, DEFAULT_SONG_POSITION)
        }
        playSong()
        return START_NOT_STICKY
    }

    override fun onPrepared(p0: MediaPlayer?) {
        mediaPlayer.start()
    }

    override fun onCompletion(p0: MediaPlayer?) {
        when (loopType) {
            LoopType.LOOP_ALL -> nextSong()
            LoopType.LOOP_ONE -> playSong()
        }
    }

    override fun onDestroy() {
        mediaPlayer.release()
        super.onDestroy()
    }

    private fun playSong() {
        mediaPlayer.stop()
        mediaPlayer.release()
        initMediaPlayer()
        val song = songs[currentSongPosition]
        mediaPlayer.setDataSource(applicationContext, Uri.parse(song.songUri))
        mediaPlayer.prepareAsync()
    }

    private fun nextSong() {
        currentSongPosition++
        if (currentSongPosition >= songs.size) {
            currentSongPosition = DEFAULT_SONG_POSITION
        }
        playSong()
    }

    fun getDuration(): Int {
        return mediaPlayer.duration
    }

    fun getCurrentPosition(): Int {
        return mediaPlayer.currentPosition
    }

    fun seekTo(progress: Int) {
        Log.d("TestMp3", "Call Seekto : $progress")
        mediaPlayer.seekTo(progress)
    }

    inner class SongBinder : Binder() {
        fun getService(): PlayingService {
            return this@PlayingService
        }
    }
}