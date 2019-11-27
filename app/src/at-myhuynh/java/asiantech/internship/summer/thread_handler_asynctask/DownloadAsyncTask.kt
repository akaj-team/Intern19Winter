package asiantech.internship.summer.thread_handler_asynctask

import android.os.AsyncTask
import android.os.Environment
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadAsyncTask : AsyncTask<String, Int, String>() {

    override fun doInBackground(vararg params: String?): String? {
        var input: InputStream? = null
        var output: OutputStream? = null
        var connection: HttpURLConnection? = null
        try {
            val url = URL(params[0])
            connection = url.openConnection() as HttpURLConnection
            connection.connect()
            if (connection.responseCode != HttpURLConnection.HTTP_OK) {
                return ("Server returned HTTP " + connection.responseCode
                        + " " + connection.responseMessage)
            }
            val fileLength = connection.contentLength
            // download the file
            input = connection.inputStream
            output = FileOutputStream(Environment.getExternalStorageDirectory().path)
            val data = ByteArray(4096)
            var total: Long = 0
            var count: Int
            while (input.read(data).also { count = it } != -1) {
                if (isCancelled) {
                    input.close()
                    return null
                }
                total += count.toLong()


                if (fileLength > 0)
                    publishProgress((total * 100 / fileLength).toInt())
                output.write(data, 0, count)
            }
        } catch (e: Exception) {
            return e.toString()
        } finally {
            try {
                output?.close()
                input?.close()
            } catch (ignored: IOException) {
            }
            connection?.disconnect()
        }
        return null
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }
}