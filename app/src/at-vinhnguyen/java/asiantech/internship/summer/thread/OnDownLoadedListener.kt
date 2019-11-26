package asiantech.internship.summer.thread

interface OnDownLoadedListener {
    fun onUpdateProcess(process: Int)

    fun onDownloaded(imgPath: String)
}
