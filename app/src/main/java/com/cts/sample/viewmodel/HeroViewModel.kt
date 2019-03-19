package com.cts.sample.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.cts.sample.di.AppController
import com.cts.sample.model.DataRepoModel
import com.cts.sample.network.DataRepository
import javax.inject.Inject

/* This is ViewModel class designed to manage UI data for MainActivity. */
class HeroViewModel : ViewModel() {

    val tag : String = HeroViewModel::class.java.simpleName

    private lateinit var heroList: MutableLiveData<DataRepoModel>

    val isLoading = ObservableBoolean()
    val isError = ObservableBoolean()

    /* Repository class object is injected using Dependency Injection. */
    @Inject
    lateinit var repository: DataRepository

    /* This function gets data for RecyclerView using injected DataRepository class instance. */
    fun fetchHeros() {
        isLoading.set(true)
        AppController.instance.getApplicationComponent()!!.inject(this)
        setListObservable(repository.getHeros()!!)
    }

    /* This function sets LiveData object. */
    private fun setListObservable(heroList: MutableLiveData<DataRepoModel>) {
        this.heroList = heroList
    }

    /* This function will return LiveData object. */
    fun getListObservable(): MutableLiveData<DataRepoModel>? {
        return heroList
    }

    /* This function is listener function for SwipeRefreshLayout. */
    fun onRefresh() {
        fetchHeros()
    }
}