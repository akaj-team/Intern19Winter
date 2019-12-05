package asiantech.internship.summer.service_broadcast_receiver

import android.net.Uri

data class Song(var name: String, var artist: String, var path: Uri, var duration: String = "00:00")
