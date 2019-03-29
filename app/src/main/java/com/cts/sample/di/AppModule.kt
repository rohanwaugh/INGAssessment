package com.cts.sample.di

import android.arch.lifecycle.ViewModelProviders
import android.util.Log
import com.cts.sample.network.HeroApi
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
    fun providesHeroApi(retrofit: Retrofit): HeroApi {
        return retrofit.create<HeroApi>(HeroApi::class.java)
    }

    /* This method will provide GsonConverterFactory object which is internally required for Retrofit dependency. */
    @Provides
    @Singleton
    fun providesRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HeroApi.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    /* This method will provide DataRepository object which can be injected. */
    @Provides
    @Singleton
    fun providesRepository(heroApi:HeroApi): DataRepository {
        return DataRepository(heroApi)
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