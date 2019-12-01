package asiantech.internship.summer.thread_handler_asynctask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-myhuynh`.activity_download.*

class DownloadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        btnDownloadAsync.setOnClickListener {
            progressBar.progress = 0
            tvRatioNumber.text = getString(R.string.textview_text_0)
            DownloadAsyncTask(this, progressBar, tvRatioNumber).execute(getString(R.string.edt_text_link_download))
        }
    }
}
