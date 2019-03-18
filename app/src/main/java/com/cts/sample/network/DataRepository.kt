package com.cts.sample.network

import android.arch.lifecycle.MutableLiveData
import com.cts.sample.di.AppController
import com.cts.sample.model.DataRepoModel
import com.cts.sample.model.DataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DataRepository {

    private var heroList: MutableLiveData<DataRepoModel>? = MutableLiveData()

    @Inject
    lateinit var webService: API

    init {
        AppController.instance.getApplicationComponent()!!.inject(this)
    }

    fun getHeros(): MutableLiveData<DataRepoModel>? {

        val call =  webService.getHeros()
        call.enqueue(object : Callback<List<DataModel>> {

            override fun onResponse(call: Call<List<DataModel>>, response: Response<List<DataModel>>) {
                heroList?.value = DataRepoModel(response.body()!!)
            }

            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                heroList?.value = DataRepoModel(t)
            }
        })

        return heroList
    }
}