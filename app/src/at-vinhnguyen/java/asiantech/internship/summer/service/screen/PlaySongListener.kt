package asiantech.internship.summer.service.screen

interface PlaySongListener {
    fun onPlay()

    fun onPause()

    fun onNext()

    fun onPrevious()

    fun changeLoop()

    fun changeShuffle()
}