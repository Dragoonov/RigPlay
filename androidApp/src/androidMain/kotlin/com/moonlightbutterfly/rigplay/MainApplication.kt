package com.moonlightbutterfly.rigplay

import android.app.Application
import com.moonlightbutterfly.rigplay.data.di.dataModule
import com.moonlightbutterfly.rigplay.di.domainModule
import com.moonlightbutterfly.rigplay.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(dataModule + domainModule + presentationModule)
        }
    }
}