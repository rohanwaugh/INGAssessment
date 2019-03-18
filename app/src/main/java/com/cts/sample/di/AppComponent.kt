package com.cts.sample.di

import com.cts.sample.network.DataRepository
import com.cts.sample.viewmodel.HeroViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(dataRepository: DataRepository)
    fun inject(cardsListViewModel: HeroViewModel)
}