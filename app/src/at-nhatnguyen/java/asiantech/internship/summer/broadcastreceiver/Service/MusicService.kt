package asiantech.internship.summer.broadcastreceiver.Service


import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.os.PowerManager
import android.util.Log
import androidx.core.app.NotificationCompat
import asiantech.internship.summer.R
import asiantech.internship.summer.broadcastreceiver.MusicActivity
import asiantech.internship.summer.broadcastreceiver.model.SongModel

class MusicService : Service(), MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private lateinit var uri: Uri
    private var mediaPlayer: MediaPlayer? = null
    private var listSong: MutableList<SongModel>? = null
    private var songPosition: Int = 0
    private val musicBind = MusicBinder()
    private var songName = ""
    private val CHANNEL_ID = "ForegroundService"
    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
        initMusicPlayer()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
        createNotification()
    }

    private fun initMusicPlayer() {
        mediaPlayer?.setWakeMode(applicationContext, PowerManager.PARTIAL_WAKE_LOCK)
        mediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer?.setOnPreparedListener(this)
        mediaPlayer?.setOnCompletionListener(this)
        mediaPlayer?.setOnErrorListener(this)
    }

    fun setList(songModel: MutableList<SongModel>) {
        this.listSong = songModel
    }

    inner class MusicBinder : Binder() {
        internal val getService: MusicService
            get() = this@MusicService
    }

    override fun onBind(intent: Intent?): IBinder? {
        return musicBind
    }

    override fun onUnbind(intent: Intent?): Boolean {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        return false
    }

    fun setSongPosition(position: Int) {
        songPosition = position
    }

    fun playSong() {
        mediaPlayer?.reset()
        val songModel = listSong?.get(songPosition)
        songName = songModel?.songName.toString()

        Log.d("xxx", "$songPosition")

        // val currSong = playSong?.songId
        uri = Uri.parse(songModel?.path)
        // val trackUri = currSong?.let { ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, it) }
        try {
            uri.let { mediaPlayer?.setDataSource(applicationContext, it) }
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
        mediaPlayer?.prepare()
        mediaPlayer?.start()
    }


    override fun onCompletion(mp: MediaPlayer?) {
        if (mediaPlayer?.currentPosition!! > 0) {
            mp?.reset()
            playNext()
        }
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        mp?.reset()
        return false
    }


    fun seekBar(position: Int) {
        mediaPlayer?.seekTo(position)
    }

    fun pause() {
        mediaPlayer?.pause()
    }

    fun start() {
        mediaPlayer?.start()
    }

    fun playPrev() {
        songPosition--
        if (songPosition < 0) {
            songPosition = listSong!!.size - 1
        }
        playSong()
    }

    fun playNext() {
        songPosition++
        if (songPosition >= listSong?.size!!) {
            songPosition = 0
        }
        playSong()
    }

    fun getPosition(): Int {
        return songPosition
    }

    fun getDuration(): Int? {
        return mediaPlayer?.duration
    }

    fun media(): MediaPlayer? {
        return mediaPlayer
    }

    fun getCurrentPosition(): Int? {
        return mediaPlayer?.currentPosition
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)
    }

    private fun createNotification() {
        // val songModel = listSong?.get(songPosition)
        val notificationIntent = Intent(this, MusicActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val notifications = NotificationCompat.Builder(this, CHANNEL_ID)
        notifications.apply {
            setContentTitle("Playing")
            setContentText(songName)
            setSmallIcon(R.drawable.ic_play_arrow)
            addAction(R.drawable.ic_play_arrow,"",pendingIntent)
            setContentIntent(pendingIntent)
        }
        val builder = notifications.build()
        startForeground(1, builder)
    }
}
