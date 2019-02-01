package com.example.framgia.architecture

import android.app.Application
import com.example.framgia.architecture.di.rootModule
import org.koin.android.ext.android.startKoin

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, rootModule)
    }
}
