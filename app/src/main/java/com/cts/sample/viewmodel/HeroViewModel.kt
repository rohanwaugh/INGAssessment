package com.cts.sample.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.util.Log
import com.cts.sample.model.DataRepoModel
import com.cts.sample.network.DataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HeroViewModel : ViewModel() {

    val tag : String = HeroViewModel::class.java.simpleName

    //this is the data that we will fetch asynchronously
    var heroList: MutableLiveData<DataRepoModel>? = null

    val isLoading = ObservableBoolean()
    val isError = ObservableBoolean()
    var repository: DataRepository? = null

    init {
        repository = DataRepository()
    }

    fun fetchHeros() {
        isLoading.set(true)
        setListObservable(repository!!.getHeros()!!)
    }

    private fun setListObservable(heroList: MutableLiveData<DataRepoModel>) {
        this.heroList = heroList
    }

    fun getListObservable(): MutableLiveData<DataRepoModel>? {
        return heroList
    }


    override fun onCleared() {
        super.onCleared()
    }

    fun onRefresh() {
        fetchHeros()
    }
}