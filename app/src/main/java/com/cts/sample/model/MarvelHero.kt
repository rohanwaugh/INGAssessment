package com.cts.sample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
/* Data Model class. */
@Parcelize
data class MarvelHero(
    val name: String,
    val realname :String,
    val team : String,
    val firstappearance : String,
    val createdby : String,
    val imageurl: String) : Parcelable


