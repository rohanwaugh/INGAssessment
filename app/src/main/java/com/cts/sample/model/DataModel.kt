package com.cts.sample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModel(val name: String,
                     val realname :String,
                     val team : String,
                     val firstappearance : String,
                     val createdby : String,
                     val publisher :String,
                     val imageurl: String) : Parcelable


