package com.cts.sample.di

import android.arch.lifecycle.ViewModelProviders
import com.cts.sample.network.HeroApi
import com.cts.sample.network.HeroRepository
import com.cts.sample.ui.MainActivity
import com.cts.sample.util.Constants
import com.cts.sample.viewmodel.HeroViewModel
import com.cts.sample.viewmodel.HeroViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/* Module class which is designed to provide objects which can be injected. */
@Module
class AppModule(private val mainActivity: MainActivity) {

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
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    /* This method will provide HeroRepository object which can be injected. */
    @Provides
    @Singleton
    fun providesRepository(heroApi:HeroApi): HeroRepository {
        return HeroRepository(heroApi)
    }

    @Provides
    @Singleton
    fun providesHeroViewModel(heroViewModelFactory: HeroViewModelFactory) : HeroViewModel {
        return ViewModelProviders.of(mainActivity,heroViewModelFactory).get<HeroViewModel>(HeroViewModel::class.java)
    }


    @Provides
    @Singleton
    fun providesHeroViewModelFactory(heroRepository: HeroRepository): HeroViewModelFactory =
        HeroViewModelFactory(heroRepository)

}