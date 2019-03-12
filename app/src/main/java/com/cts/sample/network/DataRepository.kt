package com.cts.sample.network

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.cts.sample.model.DataRepoModel
import com.cts.sample.model.DataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepository {

    private var heroList: MutableLiveData<DataRepoModel>? = MutableLiveData()

    fun getHeros(): MutableLiveData<DataRepoModel>? {

        Log.i("XXX","Inside getHeros")
        val call = WebAccess.heroAPI.getHeros();
        Log.i("XXX", "call object created $call")
        call.enqueue(object : Callback<List<DataModel>> {

            override fun onResponse(call: Call<List<DataModel>>, response: Response<List<DataModel>>) {
                Log.i("XXX","Inside onResponse")
                heroList?.value = DataRepoModel(response.body()!!)

            }

            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                Log.i("XXX","Inside onFailure")
                heroList?.value = DataRepoModel(t)
            }
        })

        return heroList
    }
}