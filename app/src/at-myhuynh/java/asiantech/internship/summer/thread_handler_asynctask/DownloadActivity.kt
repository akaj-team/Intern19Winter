package asiantech.internship.summer.thread_handler_asynctask

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-myhuynh`.activity_download.*

class DownloadActivity : AppCompatActivity() {

    private lateinit var handler: Handler

    companion object {
        private const val START_PROGRESS = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        btnDownloadHandler.setOnClickListener {
            progressBar.progress = 0
            tvRatioNumber.text = getString(R.string.textview_text_0)
            Thread(DownloadThreadHandler(this, getString(R.string.edt_text_link_download), handler)).start()
        }

        btnDownloadAsync.setOnClickListener {
            progressBar.progress = 0
            tvRatioNumber.text = getString(R.string.textview_text_0)
            DownloadAsyncTask(this, progressBar, tvRatioNumber).execute(getString(R.string.edt_text_link_download))
        }
    }

    override fun onResume() {
        super.onResume()
        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if (msg.what == START_PROGRESS) {
                    progressBar.progress = msg.arg1
                    tvRatioNumber.text = msg.arg1.toString()
                }
            }
        }
    }
}
