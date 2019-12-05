package asiantech.internship.summer.service_broadcast_receiver

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri

class Utils {

    companion object {
        fun getCoverPicture(context: Context, uri: Uri): Bitmap? {
            val mmr = MediaMetadataRetriever()
            val rawArt: ByteArray?
            val bfo = BitmapFactory.Options()

            mmr.setDataSource(context, uri)
            rawArt = mmr.embeddedPicture

            if (null != rawArt) return BitmapFactory.decodeByteArray(rawArt, 0, rawArt.size, bfo)
            return null
        }
    }
}
