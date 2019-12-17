package asiantech.internship.summer.service.screen

import android.Manifest
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import asiantech.internship.summer.service.model.Song
import asiantech.internship.summer.service.utils.Navigator
import asiantech.internship.summer.service.utils.OnRecyclerViewItemClick
import asiantech.internship.summer.service.utils.SongUtils
import asiantech.internship.summer.service.utils.SongUtils.DEFAULT_SONG_POSITION
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.`at-vinhnguyen`.activity_soundclound.*

class SoundCloudActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, OnRecyclerViewItemClick {

    companion object {
        private const val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 100
        var songs = ArrayList<Song>()
    }

    var currentSongId = DEFAULT_SONG_POSITION

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
                    Toast.makeText(this, "Permission denied to write your External storage", Toast.LENGTH_SHORT).show()
                } else {
                    bottomNavigationHome.selectedItemId = R.id.itemPlaylist
                }
            }
        }
    }

    private fun initView() {
        bottomNavigationHome.setOnNavigationItemSelectedListener(this)
        songs = SongUtils.getSongsInDevice(this)
    }

    private fun checkPermissionAndLoadSongs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
        }
    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        return when (p0.itemId) {
            R.id.itemPlaylist -> {
                Navigator.loadFragment(supportFragmentManager, R.id.frmContainer, PlayListFragment.newInstance(songs), false, PlayListFragment::class.java.simpleName)
                true
            }
            R.id.itemPlaying -> {
                Navigator.loadFragment(supportFragmentManager, R.id.frmContainer, PlayingFragment.newInstance(songs, currentSongId), false, PlayListFragment::class.java.simpleName)
                true
            }
            else -> false
        }
    }

    override fun onRecyclerViewItemClickListener(songId: Int) {
        currentSongId = songId
        bottomNavigationHome.selectedItemId = R.id.itemPlaying
    }
}
