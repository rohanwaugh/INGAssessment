package com.cts.sample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
/* Data Model class. */
@Parcelize
data class MarvelHero(
    var name: String,
    var realname :String,
    val team : String,
    val firstappearance : String,
    var createdby : String,
    val imageurl: String) : Parcelable


