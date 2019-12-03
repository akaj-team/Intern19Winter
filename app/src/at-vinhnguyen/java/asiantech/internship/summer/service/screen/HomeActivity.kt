package asiantech.internship.summer.service.screen

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import asiantech.internship.summer.service.model.Song
import asiantech.internship.summer.service.utils.Navigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.`at-vinhnguyen`.activity_soundclound.*

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
        private const val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 100
        public val MUSIC_URI: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
    }

    private var songsList = ArrayList<Song>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soundclound)
        checkPermissionAndLoadSongs()
        initView()
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

    private fun initView() {
        bottomNavigationHome.setOnNavigationItemSelectedListener(this)
        bottomNavigationHome.selectedItemId = R.id.itemPlaylist
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
                MediaStore.Audio.Media.DURATION)

        val cursor = contentResolver.query(MUSIC_URI, null, null, null, MediaStore.Audio.Media.TITLE)
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



    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.itemPlaylist -> {
                Toast.makeText(this, R.id.itemPlaylist.toString(), Toast.LENGTH_SHORT).show()
                Navigator.loadFragment(supportFragmentManager, R.id.frmContainer, PlayListFragment.newInstance(songsList), true, PlayListFragment.javaClass.simpleName)
                return true
            }
            R.id.itemPlaying -> {
                Toast.makeText(this, R.id.itemPlaylist.toString(), Toast.LENGTH_SHORT).show()
                Navigator.loadFragment(supportFragmentManager, R.id.frmContainer, PlayingFragment.newInstance(1), true, PlayListFragment.javaClass.simpleName)
                return true
            }
            else -> return false
        }
    }
}
