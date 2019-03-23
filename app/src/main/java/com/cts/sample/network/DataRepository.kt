package com.cts.sample.network

import com.cts.sample.model.DataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/* This is Repository class designed to fetch data from network. */
class DataRepository(val webService: API) {

    fun getHeros(success: ((List<DataModel>?) -> Unit)?, failure: ((String) -> Unit)?){

        val heroApi =  webService.getHeros()
        heroApi.enqueue(object : Callback<List<DataModel>> {
            override fun onFailure(call: Call<List<DataModel>>, t: Throwable?) {
                failure?.invoke("No Data")
            }

            override fun onResponse(call: Call<List<DataModel>>, response: Response<List<DataModel>>) {
                success?.invoke(response.body())
            }
        })
    }
}