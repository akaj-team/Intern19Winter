package asiantech.internship.summer.service_broadcast_receiver

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri

class Utils {

    companion object {
        const val SONG_ART_NAME = "<unknown>"
        const val SONG_ART = "SONG_ART"
        const val SONG_NAME = "SONG_NAME"
        const val SONG_URI = "SONG_URI"
        const val ARG_SONG = "ARG_SONG"
        const val ARG_Is_PLAYING = "ARG_Is_PLAYING"
        const val ARG_STATE_SONG = "ARG_STATE_SONG"
        const val ACTION_PAUSE = "ACTION_PAUSE"
        const val ACTION_PLAY = "ACTION_PLAY"
        const val ACTION_START = "ACTION_START"
        const val STATE_PLAYING = "STATE_PLAYING"
        const val STATE_NEED_PLAY = "STATE_NEED_PLAY"

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
