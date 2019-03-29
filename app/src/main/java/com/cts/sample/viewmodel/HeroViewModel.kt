package com.cts.sample.viewmodel

import android.arch.lifecycle.*
import android.databinding.ObservableBoolean
import com.cts.sample.network.Data
import com.cts.sample.network.DataRepository
import com.cts.sample.network.State
import com.cts.sample.util.Constants


/* This is ViewModel class designed to manage UI data for MainActivity. */
class HeroViewModel (private val repository : DataRepository): ViewModel() {

    var heroList : MutableLiveData<Data> = MutableLiveData()
    private var data: Data? = null

    val isLoading = ObservableBoolean()
    val isError = ObservableBoolean()

    fun fetchHeros() {
        isLoading.set(true)
        repository.getHeros(
            success = {
                data = Data(State.SUCCESS,it,Constants.SUCCESS_MSG)
                heroList.postValue(data)
            },
            failure = {
                data = Data(State.ERROR,null,Constants.ERROR_MSG)
                heroList.postValue(data)

            }
        )
    }

    /* This function is listener function for SwipeRefreshLayout. */
    fun onRefresh() {
        fetchHeros()
    }
}

class HeroViewModelFactory(private val dataRepository: DataRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HeroViewModel(dataRepository) as T
    }
}