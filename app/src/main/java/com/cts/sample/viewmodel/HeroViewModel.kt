package com.cts.sample.viewmodel

import android.arch.lifecycle.*
import android.databinding.ObservableBoolean
import com.cts.sample.model.DataModel
import com.cts.sample.network.DataRepository


/* This is ViewModel class designed to manage UI data for MainActivity. */
class HeroViewModel (val repository : DataRepository): ViewModel() {

    var heroList: MutableLiveData<List<DataModel>>? = MutableLiveData()

    val isLoading = ObservableBoolean()
    val isError = ObservableBoolean()

    fun fetchHeros() {
        isLoading.set(true)
        repository.getHeros(
            success = {
                heroList?.value = it
                isLoading.set(false)
                isError.set(false)
            },
            failure = {
                isLoading.set(false)
                heroList?.value =null
                isError.set(true)
            }
        )
    }

    /* This function is listener function for SwipeRefreshLayout. */
    fun onRefresh() {
        fetchHeros()
    }
}

class HeroViewModelFactory(private val dataRepository: DataRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HeroViewModel(dataRepository) as T
    }
}