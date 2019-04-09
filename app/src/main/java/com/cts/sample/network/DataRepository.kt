package com.cts.sample.network

import com.cts.sample.model.MarvelHero
import com.cts.sample.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/* This is Repository class designed to fetch data from network. */
class DataRepository(private val heroApi: HeroApi) {

    /* This function will fetch the data from network using Retrofit and set it to ViewModel LiveData using Lambda expression. */
    fun getHeros(success: ((List<MarvelHero>?) -> Unit)?, failure: ((String) -> Unit)?){

        val heroApi =  heroApi.getHeros()
        heroApi.enqueue(object : Callback<List<MarvelHero>> {
            override fun onFailure(call: Call<List<MarvelHero>>, t: Throwable?) {
                failure?.invoke(Constants.ERROR_MSG)
            }

            override fun onResponse(call: Call<List<MarvelHero>>, response: Response<List<MarvelHero>>) {
                success?.invoke(response.body())
            }
        })
    }
}