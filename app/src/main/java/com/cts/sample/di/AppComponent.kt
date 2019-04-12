package com.cts.sample.di

import com.cts.sample.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

/* Component interface used by Dagger2 to generate code. */
@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}