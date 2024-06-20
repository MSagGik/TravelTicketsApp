package com.msaggik.travelticketsapp.root

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.msaggik.flights.di.dataModule
import com.msaggik.flights.di.domainModule
import com.msaggik.flights.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        startKoin {
            androidContext(this@App)
            modules(dataModule, domainModule, viewModelModule)
        }
    }
}