package com.example.retripapp.data

import android.os.Parcel
import android.os.Parcelable
import java.util.Date

data class Destinasi(
    var id : String?,
    var nama : String?,
    var lokasi : String?,
    var category : String?,
    var deskripsi : String?,
    var img : String?,
    var lat : String?,
    var lang : String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nama)
        parcel.writeString(lokasi)
        parcel.writeString(category)
        parcel.writeString(deskripsi)
        parcel.writeString(img)
        parcel.writeString(lat)
        parcel.writeString(lang)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Destinasi> {
        override fun createFromParcel(parcel: Parcel): Destinasi {
            return Destinasi(parcel)
        }

        override fun newArray(size: Int): Array<Destinasi?> {
            return arrayOfNulls(size)
        }
    }
}

data class Ulasan(
    val id : String?,
    val name : String?,
    val rating : Float?,
    val ulasan : String?,
    val date : String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeValue(rating)
        parcel.writeString(ulasan)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ulasan> {
        override fun createFromParcel(parcel: Parcel): Ulasan {
            return Ulasan(parcel)
        }

        override fun newArray(size: Int): Array<Ulasan?> {
            return arrayOfNulls(size)
        }
    }
}