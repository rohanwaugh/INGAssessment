package com.cts.sample.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.cts.sample.di.AppController
import com.cts.sample.model.DataRepoModel
import com.cts.sample.network.DataRepository
import javax.inject.Inject


class HeroViewModel : ViewModel() {

    val tag : String = HeroViewModel::class.java.simpleName

    //this is the data that we will fetch asynchronously
    var heroList: MutableLiveData<DataRepoModel>? = null

    val isLoading = ObservableBoolean()
    val isError = ObservableBoolean()

    @Inject
    lateinit var repository: DataRepository


    fun fetchHeros() {
        isLoading.set(true)
        AppController.Companion.instance.getApplicationComponent()!!.inject(this)
        setListObservable(repository.getHeros()!!)
    }

    private fun setListObservable(heroList: MutableLiveData<DataRepoModel>) {
        this.heroList = heroList
    }

    fun getListObservable(): MutableLiveData<DataRepoModel>? {
        return heroList
    }


    fun onRefresh() {
        fetchHeros()
    }
}