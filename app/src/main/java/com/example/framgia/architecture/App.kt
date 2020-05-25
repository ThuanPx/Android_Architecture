package com.example.framgia.architecture

import android.app.Application
import com.example.framgia.architecture.di.appModule
import com.example.framgia.architecture.di.networkModule
import com.example.framgia.architecture.di.repositoryModule
import com.example.framgia.architecture.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        configKoin()
        configTimber()
    }

    private fun configKoin() {
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, networkModule, repositoryModule, viewModelModule))
        }
    }

    private fun configTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
