package asiantech.internship.summer.service.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.provider.MediaStore
import asiantech.internship.summer.R
import asiantech.internship.summer.service.model.Song
import java.io.ByteArrayInputStream

object SongUtils {
    const val EXTRA_SONGS = "extra_songs"
    const val EXTRA_SONG_POSITION = "song_position"
    const val DEFAULT_SONG_POSITION = 0
    val MUSIC_URI: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
    fun getSongArt(path: String, context: Context): Bitmap {
        val retriever = MediaMetadataRetriever()
        var bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.default_song)
        try {
            retriever.setDataSource(path)
            val embePicStream = ByteArrayInputStream(retriever.embeddedPicture)
            embePicStream.apply {
                bitmap = BitmapFactory.decodeStream(embePicStream)
            }
            return bitmap
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            retriever.release()
        }
        return bitmap
    }

    fun getSongsInDevice(context: Context): ArrayList<Song> {
        val songList = ArrayList<Song>()
        val cursorCols = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA
        )

        val cursor = context.contentResolver?.query(MUSIC_URI, cursorCols, null, null, MediaStore.Audio.Media.TITLE)
        cursor?.let {
            it.moveToFirst()
            val idIndex = it.getColumnIndex(cursorCols[0])
            val titleIndex = it.getColumnIndex(cursorCols[1])
            val artistIndex = it.getColumnIndex(cursorCols[2])
            val durationIndex = it.getColumnIndex(cursorCols[3])
            val songArtIndex = it.getColumnIndex(cursorCols[4])
            while (!it.isAfterLast) {
                songList.add(Song(it.getInt(idIndex), it.getString(titleIndex), it.getString(artistIndex), MUSIC_URI.toString() + it.getString(idIndex), it.getInt(durationIndex), it.getString(songArtIndex)))
                it.moveToNext()
            }
        }
        cursor?.close()
        return songList
    }

    fun convertToStringTime(milisecond: Int): String {
        return (String.format("%02d", milisecond / 1000 / 60) + " : " + String.format("%02d", milisecond / 100 % 60))
    }
}