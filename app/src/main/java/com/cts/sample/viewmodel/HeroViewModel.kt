package com.cts.sample.viewmodel

import android.arch.lifecycle.*
import android.databinding.ObservableBoolean
//import com.cts.sample.network.Data
import com.cts.sample.network.DataRepository
import com.cts.sample.network.Result
import com.cts.sample.util.Constants


/* This is ViewModel class designed to manage UI data for MainActivity. */
class HeroViewModel (private val repository : DataRepository): ViewModel() {

    // heroList is a private variable of type of MutableLiveData hence not exposed to MainActivity.
    private var heroList : MutableLiveData<Result> = MutableLiveData()

    val isLoading = ObservableBoolean()
    val isError = ObservableBoolean()

    /*
     * This function  uses DataRepository class object to fetch the data from Network.
     * Data returned from repository is set to LiveData inside success and failure lambda.
     *
     * */
    fun fetchHeros() {
        isLoading.set(true)
        repository.getHeros(
            success = {
                heroList.postValue(Result.SUCCESS(it,Constants.SUCCESS_MSG))
            },
            failure = {
                heroList.postValue(Result.ERROR(Constants.ERROR_MSG))

            }
        )
    }

    /* This function exposes heroList as LiveData object to MainActivity. */
    fun getHeroList(): LiveData<Result> = heroList


    /* This function is listener function for SwipeRefreshLayout. */
    fun onRefresh() {
        fetchHeros()
    }
}

/* This is ViewModel Factory class. */
class HeroViewModelFactory(private val dataRepository: DataRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HeroViewModel(dataRepository) as T
    }
}