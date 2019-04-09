package com.cts.sample.network

import com.cts.sample.model.MarvelHero

/* This is Sealed class for handling Success and Error scenarios. */
sealed class Result {

//    object SUCCESS : Result()
//    object ERROR   : Result()
    class SUCCESS(val data: List<MarvelHero>?) : Result()
    class ERROR(val message:String)   : Result()

//    data class Success<out T>(val data: T) : Result<T>()
//    data class ERROR<out T>(val message:T) : Result<T>()
}

//data class Data(
//    var state :Result,
//    var data:List<DataModel>? = null,
//    var message:String? = null
//
//
//)