package com.cts.sample.di

import android.app.Application

class AppController : Application() {

    var component: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        instance = this
        setupDaggerComponent()

    }

    fun setupDaggerComponent() {
        component = DaggerAppComponent.builder().appModule( AppModule()).build()
    }

    fun getApplicationComponent(): AppComponent? {
        return component
    }

    companion object {
        lateinit var instance: AppController
    }
}