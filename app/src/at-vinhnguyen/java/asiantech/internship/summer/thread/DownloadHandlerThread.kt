package asiantech.internship.summer.thread

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import asiantech.internship.summer.thread.HandlerActivity.Companion.DOWNLOADED_PROCESS
import asiantech.internship.summer.thread.HandlerActivity.Companion.IMAGE_PATH
import asiantech.internship.summer.thread.HandlerActivity.Companion.UPDATE_PROCESS
import java.io.*
import java.net.MalformedURLException
import java.net.URL

class DownloadHandlerThread(val context: Context, private val handler: Handler, private val urlPath: String) : Runnable {
    override fun run() {
        val imgLength: Int
        val imgFile: File
        try {
            val url = URL(urlPath)
            val connection = url.openConnection()
            connection.connect()
            imgLength = connection.contentLength
            val imgFolder = File(context.getExternalFilesDir(null)?.absolutePath, "tempdownload")
            if (!imgFolder.exists()) {
                imgFolder.mkdirs()
            }
            imgFile = File(imgFolder, "handlerimg.jpg")
            val outputStream: OutputStream = FileOutputStream(imgFile)
            val inputStream: InputStream = BufferedInputStream(url.openStream(), 8192)
            val data = ByteArray(1024)
            var total = 0
            var count: Int
            while (inputStream.read(data).also { count = it } != -1) {
                total += count
                outputStream.write(data, 0, count)
                val progress: Int = 100 * total / imgLength
                val message = Message()
                message.what = UPDATE_PROCESS
                message.arg1 = progress
                handler.sendMessage(message)
            }
            val message = Message()
            message.what = DOWNLOADED_PROCESS
            val bundle = Bundle()
            bundle.putString(IMAGE_PATH, imgFile.absolutePath)
            message.data = bundle
            handler.sendMessage(message)
            inputStream.close()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
    }
}
