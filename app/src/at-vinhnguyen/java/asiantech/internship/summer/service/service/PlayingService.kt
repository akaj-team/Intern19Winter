package asiantech.internship.summer.service.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.IBinder
import asiantech.internship.summer.service.model.Song
import asiantech.internship.summer.service.utils.LoopType
import asiantech.internship.summer.service.utils.SongUtils.DEFAULT_SONG_POSITION
import asiantech.internship.summer.service.utils.SongUtils.EXTRA_SONGS
import asiantech.internship.summer.service.utils.SongUtils.EXTRA_SONG_POSITION

class PlayingService : Service(), MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private lateinit var songs: ArrayList<Song>
    private lateinit var mediaPlayer: MediaPlayer
    private var currentSongPosition = 0
    private var loopType = LoopType.LOOP_ALL

    companion object {

        fun getPlayingServiceIntent(context: Context, songs: ArrayList<Song>, songId: Int): Intent {
            val playingServiceIntent = Intent(context, PlayingService::class.java)
            playingServiceIntent.putParcelableArrayListExtra(EXTRA_SONGS, songs)
            playingServiceIntent.putExtra(EXTRA_SONG_POSITION, songId)
            return playingServiceIntent
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.apply {
            songs = getParcelableArrayListExtra<Song>(EXTRA_SONGS) as ArrayList<Song>
            currentSongPosition = getIntExtra(EXTRA_SONG_POSITION, DEFAULT_SONG_POSITION)
        }
        playSong()
        return START_NOT_STICKY
    }

    private fun playSong() {
        val song = songs[currentSongPosition]
        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioAttributes(AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build())
        mediaPlayer.setDataSource(song.songUri)
        mediaPlayer.prepareAsync()
    }

    override fun onDestroy() {
        mediaPlayer.release()
        super.onDestroy()
    }

    override fun onPrepared(p0: MediaPlayer?) {
        mediaPlayer.start()
    }

    override fun onCompletion(p0: MediaPlayer?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}