package com.example.wally

import android.os.Parcel
import android.os.Parcelable

data class Wallpapers(
    var success: Boolean,
    var total_photos: Int,
    var message: String,
    var offset: Int,
    var limit: Int,
    var photos: List<Photo>
)

data class Photo(
    var url: String,
    var title: String,
    var user: Int,
    var id: Int,
    var description: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(title)
        parcel.writeInt(user)
        parcel.writeInt(id)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}

//example model
///{
//    "success": true,
//    "total_photos": 132,
//    "message": "Successfully fetched 30 of 132 photos",
//    "offset": 5,
//    "limit": 30,
//    "photos": [
//        {
//            "url": "https://api.slingacademy.com/public/sample-photos/6.jpeg",
//            "title": "Apply future response she reduce decide",
//            "user": 30,
//            "id": 6,
//            "description": "Training beautiful age four skin cultural hundred environmental ability blood go physical relate produce tough open police."
//        }]
//}
