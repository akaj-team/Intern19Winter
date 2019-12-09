package asiantech.internship.summer.broadcastreceiver.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

class SongModel(var songName: String?, var artist: String?, var duration: Int, var path: String?, var songId:Long):Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readLong())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(songName)
        parcel.writeString(artist)
        parcel.writeInt(duration)
        parcel.writeString(path)
        parcel.writeLong(songId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SongModel> {
        override fun createFromParcel(parcel: Parcel): SongModel {
            return SongModel(parcel)
        }

        override fun newArray(size: Int): Array<SongModel?> {
            return arrayOfNulls(size)
        }
    }

}
