package asiantech.internship.summer.broadcastreceiver.Service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.ContentUris
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.PowerManager
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import asiantech.internship.summer.R
import asiantech.internship.summer.broadcastreceiver.fragment.PlayMp3Fragment
import asiantech.internship.summer.broadcastreceiver.model.SongModel

class MusicService : Service(), MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private var mediaPlayer: MediaPlayer? = null
    private var listSong: MutableList<SongModel>? = null
    private var songPosition: Int = 0
    private val musicBind = MusicBinder()
    private var songName = ""
    private val NOTIFY_ID = 1
    override fun onCreate() {
        super.onCreate()
        songPosition
        mediaPlayer = MediaPlayer()
        initMusicPlayer()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
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
       // Log.d("xxx","${listSong?.size}")
    }

//    fun setPosition(songPosition:Int){
//        this.songPosition = songPosition
//    }

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

    private fun playSong() {
        mediaPlayer?.reset()
        val playSong = listSong?.get(songPosition)
        songName = playSong?.songName.toString()

        val currSong = playSong?.songId
        val trackUri = currSong?.let { ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, it) }
        try {
            trackUri?.let { mediaPlayer?.setDataSource(applicationContext, it) }
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
        mediaPlayer?.prepareAsync()
    }

//    fun setSong(songIndex:Int){
//        songPosition = songIndex
//    }

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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
        val notIntent = Intent(this, PlayMp3Fragment::class.java)
        notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val penDInt = PendingIntent.getActivities(this, 0, arrayOf(notIntent), PendingIntent.FLAG_CANCEL_CURRENT)
        val builder = Notification.Builder(this)
        builder.setContentIntent(penDInt)
                .setSmallIcon(R.drawable.ic_play_arrow)
                .setTicker(songName)
                .setOngoing(true)
                .setContentTitle("Playing")
                .setContentText(songName)
        val not = builder.build()
        startForeground(NOTIFY_ID, not)
    }

    fun getPosition(): Int? {
        return mediaPlayer?.currentPosition
    }

    fun seekBar(posn: Int) {
        mediaPlayer?.seekTo(posn)
    }

    fun playPrev() {
        songPosition--
        if (songPosition < 0) {
            songPosition = listSong!!.size - 1
            playSong()
        }
    }

    fun playNext() {
        songPosition++
        if (songPosition >= listSong?.size!!) {
            songPosition = 0
        }
        playSong()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)
    }
}
