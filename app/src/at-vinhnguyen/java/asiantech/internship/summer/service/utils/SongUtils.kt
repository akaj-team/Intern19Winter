package asiantech.internship.summer.service.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import asiantech.internship.summer.R
import java.io.ByteArrayInputStream

object SongUtils {
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
}