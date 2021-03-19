package com.jacque.imagegallerytest

import android.app.Application
import com.jacque.imagegallerytest.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GalleryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(Modules.networkModule, Modules.repoModule, Modules.viewModelModule))
        }
    }
}