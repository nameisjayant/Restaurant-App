package com.codingwithjks.tvapp.container

import android.app.Application
import com.codingwithjks.tvapp.di.appModule
import org.koin.core.context.startKoin

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}