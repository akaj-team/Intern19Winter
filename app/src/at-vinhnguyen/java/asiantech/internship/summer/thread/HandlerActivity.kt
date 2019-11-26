package asiantech.internship.summer.thread

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-vinhnguyen`.activity_thread.*

class HandlerActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val DOWNLOADED_PROCESS = 101
        const val UPDATE_PROCESS = 100
        const val IMAGE_PATH = "image_path"
        const val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 100
    }

    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        initView()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
        }
    }

    private fun initView() {
        btnStart.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                R.id.btnStart -> {
                    val downloadHandlerThread = Thread(DownloadHandlerThread(this, handler, "https://secure.i.telegraph.co.uk/multimedia/archive/03290/Steven_Gerrard_3290268b.jpg"))
                    downloadHandlerThread.start()
                }
            }
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE -> {
                if (grantResults.isEmpty()
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when (msg.what) {
                    UPDATE_PROCESS -> {
                        progressBarDownload.progress = msg.arg1
                        tvProcess.text = msg.arg1.toString()
                    }
                    DOWNLOADED_PROCESS -> {
                        val bundle = msg.data
                        val imgPath = bundle.getString(IMAGE_PATH)
                        imgDownload.setImageDrawable(Drawable.createFromPath(imgPath))
                    }
                }
            }
        }
    }
}
