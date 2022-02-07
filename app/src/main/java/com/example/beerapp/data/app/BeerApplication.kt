package com.example.beerapp.data.app

import android.app.Application
import com.example.beerapp.di.appModule
import com.example.beerapp.di.dataModule
import com.example.beerapp.di.domainModule
import com.example.beerapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BeerApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BeerApplication)
            modules(listOf(appModule, dataModule, domainModule,presentationModule))
        }
    }
}