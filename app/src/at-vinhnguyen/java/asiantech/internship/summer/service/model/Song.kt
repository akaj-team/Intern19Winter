package asiantech.internship.summer.service.model

import android.os.Parcel
import android.os.Parcelable

data class Song(val id: Int,
                val title: String,
                val artist: String,
                val songUri: String,
                val duration: Int,
                val songArt: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readInt(),
            parcel.readString().toString()) {
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.apply {
            writeInt(id)
            writeString(title)
            writeString(artist)
            writeString(songUri)
            writeInt(duration)
            writeString(songArt)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Song> {
        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel)
        }

        override fun newArray(size: Int): Array<Song?> {
            return arrayOfNulls(size)
        }
    }
}