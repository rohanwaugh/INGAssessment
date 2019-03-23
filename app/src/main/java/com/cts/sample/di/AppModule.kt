package com.cts.sample.di

import android.arch.lifecycle.ViewModelProviders
import com.cts.sample.network.API
import com.cts.sample.network.DataRepository
import com.cts.sample.ui.MainActivity
import com.cts.sample.viewmodel.HeroViewModel
import com.cts.sample.viewmodel.HeroViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/* Module class which is designed to provide objects which can be injected. */
@Module
class AppModule(val mainActivity: MainActivity) {

    /* This method will provide Retrofit object which can be injected. */
    @Provides
    @Singleton
    fun webService(retrofit: Retrofit): API {
        return retrofit.create<API>(API::class.java)
    }

    /* This method will provide GsonConverterFactory object which is internally required for Retrofit dependency. */
    @Provides
    @Singleton
    fun retrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun gsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    /* This method will provide DataRepository object which can be injected. */
    @Provides
    @Singleton
    fun repository(webService:API): DataRepository {
        return DataRepository(webService)
    }

    @Provides
    @Singleton
    fun providesHeroViewModel(heroViewModelFactory: HeroViewModelFactory) : HeroViewModel {
        return ViewModelProviders.of(mainActivity,heroViewModelFactory).get<HeroViewModel>(HeroViewModel::class.java)
    }


    @Provides
    fun providesHeroViewModelFactory(dataRepository: DataRepository): HeroViewModelFactory =
        HeroViewModelFactory(dataRepository)

}