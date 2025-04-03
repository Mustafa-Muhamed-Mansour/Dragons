package com.dragons.app

import android.app.Application
import com.dragons.app.di.initKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}