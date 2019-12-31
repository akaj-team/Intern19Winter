package asiantech.internship.summer.service_broadcast_receiver

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import java.util.concurrent.TimeUnit

class Utils {

    companion object {
        const val SONG_ART_NAME = "<unknown>"
        const val SONG_ART = "SONG_ART"
        const val SONG_NAME = "SONG_NAME"
        const val SONG_URI = "SONG_URI"
        const val ARG_SONG = "ARG_SONG"
        const val ARG_Is_PLAYING = "ARG_Is_PLAYING"
        const val ARG_LIST_SONG = "ARG_LIST_SONG"
        const val ARG_POSITION_SONG = "ARG_POSITION_SONG"
        const val ACTION_PAUSE_NOTIFY = "ACTION_PAUSE_NOTIFY"
        const val ACTION_BACK_NOTIFY = "ACTION_BACK_NOTIFY"
        const val ACTION_NEXT_NOTIFY = "ACTION_NEXT_NOTIFY"
        const val ACTION_CLEAR_NOTIFY = "ACTION_CLEAR_NOTIFY"
        const val ACTION_NEXT_AUTO = "ACTION_NEXT_AUTO"

        private const val KEY_SHUFFLE = "KEY_SHUFFLE"
        private const val KEY_REPEAT = "KEY_REPEAT"
        private const val KEY_POSITION = "KEY_POSITION"
        private const val PREFERENCE = "MyMusic"

        fun getCoverPicture(context: Context, uri: Uri): Bitmap? {
            val mmr = MediaMetadataRetriever()
            val rawArt: ByteArray?
            val bfo = BitmapFactory.Options()

            mmr.setDataSource(context, uri)
            rawArt = mmr.embeddedPicture

            if (null != rawArt) return BitmapFactory.decodeByteArray(rawArt, 0, rawArt.size, bfo)
            return null
        }

        fun convertLongToTime(timePlayed: Long): String {
            val minutes = TimeUnit.MILLISECONDS.toMinutes(timePlayed)
            val seconds = TimeUnit.MILLISECONDS.toSeconds(timePlayed - TimeUnit.MINUTES.toMillis(minutes))
            var duration: String

            duration = if (minutes < 10) {
                "0$minutes"
            } else {
                "$minutes"
            }

            duration += if (seconds < 10) {
                ":0$seconds"
            } else {
                ":$seconds"
            }
            return duration
        }

        fun writeSharedPreferences(context: Context, isShuffle: Boolean, repeat: Int) {
            val preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
            val preferencesEditor = preferences.edit()
            preferencesEditor.putBoolean(KEY_SHUFFLE, isShuffle)
            preferencesEditor.putInt(KEY_REPEAT, repeat)
            preferencesEditor.apply()
        }

        fun readIsShuffle(context: Context): Boolean {
            val preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
            return preferences.getBoolean(KEY_SHUFFLE, false)
        }

        fun readAudioRepeat(context: Context): Int {
            val preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
            return preferences.getInt(KEY_REPEAT, 0)
        }

        fun writePositionPreferences(context: Context, position: Int) {
            val preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
            val preferencesEditor = preferences.edit()
            preferencesEditor.putInt(KEY_POSITION, position)
            preferencesEditor.apply()
        }

        fun readAudioPosition(context: Context): Int {
            val preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
            return preferences.getInt(KEY_POSITION, 0)
        }
    }
}
