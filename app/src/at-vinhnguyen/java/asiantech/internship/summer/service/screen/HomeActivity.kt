package asiantech.internship.summer.service.screen

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import asiantech.internship.summer.service.model.Song

class HomeActivity : AppCompatActivity() {

    companion object {
        private const val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 100
    }

    private var songsList = ArrayList<Song>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soundclound)
        checkPermissionAndLoadSongs()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE -> {
                if (grantResults.isEmpty()
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show()
                } else {
                    getSongsList()
                }
            }
        }
    }

    private fun checkPermissionAndLoadSongs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
        }
    }

    private fun getSongsList() {

        val cursorCols = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION
        )
        val MUSIC_URL: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val cursor = contentResolver.query(MUSIC_URL, cursorCols, null, null, null)
        cursor?.let {
            it.moveToFirst()
            val idIndex = it.getColumnIndex(cursorCols[0])
            val titleIndex = it.getColumnIndex(cursorCols[1])
            val artistIndex = it.getColumnIndex(cursorCols[2])
            val durationIndex = it.getColumnIndex(cursorCols[3])
            while (!it.isAfterLast) {
                songsList.add(Song(it.getInt(idIndex), it.getString(titleIndex), it.getString(artistIndex), it.getInt(durationIndex)))
                it.moveToNext()
            }
        }
        cursor?.close()
    }
}
