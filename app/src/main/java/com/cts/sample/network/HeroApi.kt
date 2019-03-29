package com.cts.sample.network

import com.cts.sample.model.DataModel
import retrofit2.Call
import retrofit2.http.GET

/* Retrofit Interface. */
interface HeroApi {

    @GET("marvel")
    fun getHeros():Call<List<DataModel>>
}