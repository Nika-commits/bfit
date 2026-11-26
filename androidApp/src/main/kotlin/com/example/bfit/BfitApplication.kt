package com.example.bfit

import android.app.Application
import di.initKoin
import org.koin.android.ext.koin.androidContext

class BfitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BfitApplication)
        }
    }
}