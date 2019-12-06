package asiantech.internship.summer.service_broadcast_receiver.`interface`

import asiantech.internship.summer.service_broadcast_receiver.Song

interface HandleOnclickSongItem {
    fun songItemOnClick(song: Song)
    fun songItemOnLongClick(song: Song)
}
