package asiantech.internship.summer.music

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import asiantech.internship.summer.R

class Music2Activity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music2)


        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("aaa", "Permission not granted")
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    111)

        } else {

            Log.d("aaa", "Permission has already been granted")
            Log.d("aaa", getMusic()[0].path)
        }


        supportFragmentManager.beginTransaction().add(R.id.frMusic, ListSongFragment.newInstance()).commit()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            111 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                    Log.d("aaa", "permission was granted")
                    Log.d("aaa", getMusic()[0].path)

                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
            else -> {
            }
        }
    }

    @SuppressLint("InlinedApi")
    private fun getMusic(): List<SongModel> {
        val listSong = mutableListOf<SongModel>()
        val contentResolver = contentResolver
        val songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val songCursor = contentResolver?.query(songUri, arrayOf(
//                MediaStore.Audio.Media.ALBUM,
//                MediaStore.Audio.Media.ARTIST,
//                MediaStore.Audio.Media.TRACK,
//                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DATA
//                MediaStore.Audio.Media.DURATION,
//                MediaStore.Audio.Media.YEAR
        ), null, null, null)
        if (songCursor != null && songCursor.moveToFirst()) {
            do {
//                val currentAlbum = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
//                val currentArtist = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
//                val currentTrack = songCursor.getInt(songCursor.getColumnIndex(MediaStore.Audio.Media.TRACK))
//                val currentTitle = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                val currentName = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                val currentPath = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DATA))
//                val currentDuration = songCursor.getInt(songCursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
//                val currentYear = songCursor.getInt(songCursor.getColumnIndex(MediaStore.Audio.Media.YEAR))
                // listSong.add(SongModel(currentAlbum, currentArtist, currentTrack, currentTitle, currentName, currentPath, currentDuration, currentYear))
                listSong.add(SongModel(currentName, currentPath))
            } while (songCursor.moveToNext())
        }
        return listSong
    }
}
