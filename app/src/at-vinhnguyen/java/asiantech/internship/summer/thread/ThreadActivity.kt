package asiantech.internship.summer.thread

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-vinhnguyen`.activity_thread.*

class ThreadActivity : AppCompatActivity(), OnDownLoadedListener, View.OnClickListener {

    companion object {
        const val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        initView()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE -> {
                if (grantResults.size > 0
                        || grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else{
                    Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initView() {
        btnStart.setOnClickListener(this)
        progressBarDownload.progress = 0
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                R.id.btnStart -> {
                    Toast.makeText(this, "click Start", Toast.LENGTH_SHORT).show()
                    DownloadAsyncTask(this).execute("http://i.imgur.com/OY2zNEb.jpg")
                }
            }
        }
    }

    override fun onUpdateProcess(process: Int) {
        progressBarDownload.progress = process
        tvProcess.text = process.toString()
    }

    override fun onDownloaded(imgPath: String) {
        imgDownload.setImageDrawable(Drawable.createFromPath(imgPath))
    }
}
