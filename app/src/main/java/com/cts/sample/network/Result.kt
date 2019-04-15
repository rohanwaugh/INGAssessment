package com.cts.sample.network

import com.cts.sample.model.MarvelHero

/* This is Sealed class for handling Success and Error scenarios. */
sealed class Result {

    data class ERROR(val status:String)   : Result()
    data class SUCCESS(val data: List<MarvelHero>?, val status: String) : Result()
}
