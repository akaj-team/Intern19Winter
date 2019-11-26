package asiantech.internship.summer.thread

import android.content.Context
import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import java.io.*
import java.net.MalformedURLException
import java.net.URL
import kotlin.math.log

class DownloadAsyncTask(var onDownLoadedListener: OnDownLoadedListener) : AsyncTask<String, Int, String>() {


    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg p0: String?): String {
        val context = onDownLoadedListener as Context
        val imgLength: Int
        var imgFile: File = File("")
        try {
            val url = URL(p0[0])
            val connection = url.openConnection()
            connection.connect()
            imgLength = connection.contentLength
            val imgFolder = File(context.getExternalFilesDir(null)?.absolutePath, "tempdownload")
            if (!imgFolder.exists()) {
                imgFolder.mkdirs()
            }
            imgFile = File(imgFolder, "asynctaskimg.jpg")
            val outputStream: OutputStream = FileOutputStream(imgFile)
            val inputStream: InputStream = BufferedInputStream(url.openStream(), 8192)
            val data = ByteArray(1024)
            var total = 0
            var count: Int
            while (inputStream.read(data).also { count = it } != -1) {
                total += count
                outputStream.write(data, 0, count)
                val progress: Int = 100 * total / imgLength
                publishProgress(progress)
                Log.i("Info", "Progress: $progress")
            }
            inputStream.close()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return imgFile.absolutePath
    }

    override fun onProgressUpdate(vararg values: Int?) {
        values.get(0)?.let { onDownLoadedListener.onUpdateProcess(it) }
    }

    override fun onPostExecute(result: String?) {
        if (result != null) {
            if (result.isNotEmpty()) {
                onDownLoadedListener.onDownloaded(result)
            }
        }
    }
}
