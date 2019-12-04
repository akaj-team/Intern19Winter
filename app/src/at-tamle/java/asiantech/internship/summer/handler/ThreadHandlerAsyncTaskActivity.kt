package asiantech.internship.summer.handler

import android.annotation.SuppressLint
import android.net.wifi.WifiConfiguration.AuthAlgorithm.strings
import android.os.*
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-tamle`.activity_thread_handler_async_task.*
import java.io.*
import java.net.MalformedURLException
import java.net.URL

@SuppressLint("Registered")
class ThreadHandlerAsyncTaskActivity : AppCompatActivity(), View.OnClickListener {
    private var mHandler: Handler? = null
    private var mInputStream: InputStream? = null
    private var mOutputStream: OutputStream? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_handler_async_task)
        btnDownloadAsyncTask.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnDownloadAsyncTask -> {
                DownloadFileUsingAsyncTask()
            }
        }
    }

    private fun close(inputStream: InputStream?, outputStream: OutputStream?) {
        if (outputStream != null) {
            outputStream.close()
        }
        if (inputStream != null) {
            inputStream.close()
        }
    }

    @SuppressLint("StaticFieldLeak")
    @Suppress("DEPRECATION")
    internal inner class DownloadFileUsingAsyncTask : AsyncTask<String, Int, String>() {
        override fun doInBackground(vararg params: String?): String {
            try {
                val url = URL(strings[0])
                val urlConnection = url.openConnection()
                urlConnection.connect()
                val fileLenght = urlConnection.contentLength
                val filePath = Environment.getExternalStorageDirectory().path
                mInputStream = BufferedInputStream(url.openStream())
                mOutputStream = FileOutputStream("$filePath/image.jpg")
                val data = ByteArray(1024)
                var total: Long = 0
                var count: Int = (mInputStream as BufferedInputStream).read(data)
                while (count != -1) {
                    total += count.toLong()
                    publishProgress((total * 100 / fileLenght).toInt())
                    mOutputStream?.write(data, 0, count)
                    count = (mInputStream as BufferedInputStream).read(data)
                }
                mOutputStream?.flush()
            } catch (e: MalformedURLException) {
                Log.d("--- error", e.message!!)
            } catch (e: IOException) {
                Log.d("--- error", e.message!!)
            } finally {
                close(mInputStream, mOutputStream)
            }
            return null.toString()

        }

        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.max = 100
        }

        @SuppressLint("SetTextI18n")
        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            progressBar.setProgress(values[0]!!)
            tvPercent.text = "${values[0]} %"
        }
    }
}