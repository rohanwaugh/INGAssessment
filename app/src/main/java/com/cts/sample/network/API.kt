package com.cts.sample.network

import com.cts.sample.model.DataModel
import retrofit2.Call
import retrofit2.http.GET

interface API {

    @GET("marvel")
    fun getHeros():Call<List<DataModel>>

    companion object {
        const val BASE_URL = "https://simplifiedcoding.net/demos/"
    }
}