package com.cts.sample.network

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebAccess {
    private val tag : String = WebAccess::class.java.simpleName

    val heroAPI: API by lazy {
        Log.i("XXX", "Creating retrofit client")
        val retrofit = Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create Retrofit client
        return@lazy retrofit.create(API::class.java)
    }
}