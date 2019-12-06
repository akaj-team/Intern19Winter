package asiantech.internship.summer.service_broadcast_receiver

import android.os.Parcel
import android.os.Parcelable

class Song(var name: String?, var artist: String?, var path: String?, var duration: String? = "00:00") : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeString(artist)
        dest?.writeString(path)
        dest?.writeString(duration)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Song> {
        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel)
        }

        override fun newArray(size: Int): Array<Song?> {
            return arrayOfNulls(size)
        }
    }
}
