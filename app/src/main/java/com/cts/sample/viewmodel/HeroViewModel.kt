package com.cts.sample.viewmodel

import android.arch.lifecycle.*
import android.databinding.ObservableBoolean
//import com.cts.sample.network.Data
import com.cts.sample.network.DataRepository
import com.cts.sample.network.Result
import com.cts.sample.util.Constants


/* This is ViewModel class designed to manage UI data for MainActivity. */
class HeroViewModel (private val repository : DataRepository): ViewModel() {

    // MutableLiveData is private propert and hence not exposed to outside world.
//    private var heroList : MutableLiveData<Data> = MutableLiveData()

    private var heroList : MutableLiveData<Result> = MutableLiveData()
 //   private var data: Data? = null

    val isLoading = ObservableBoolean()
    val isError = ObservableBoolean()


    /* This function will call getHeros method of DataRepository class. */
    fun fetchHeros() {
        isLoading.set(true)
        repository.getHeros(
            success = {
               // data = Data(Result.SUCCESS,it,Constants.SUCCESS_MSG)
               // heroList.postValue(data)

                heroList.postValue(Result.SUCCESS(it))
            },
            failure = {
                //data = Data(Result.ERROR,null,it)
                //heroList.postValue(data)
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