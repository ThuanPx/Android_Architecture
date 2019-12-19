package com.example.framgia.architecture

import android.app.Application
import com.bumptech.glide.Glide
import com.example.framgia.architecture.di.rootModule
import com.example.framgia.architecture.utils.GlideApp
import com.squareup.leakcanary.LeakCanary
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
        configLeakCanary()
    }

    private fun configKoin() {
        startKoin {
            androidContext(this@App)
            modules(rootModule)
        }
    }

    private fun configLeakCanary() {
        if (BuildConfig.DEBUG && BuildConfig.APPLICATION_ID == VARIANT_DEV) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return
            }
            LeakCanary.install(this)
        }
    }

    private fun configTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun onLowMemory() {
        GlideApp.get(this).onLowMemory()
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        GlideApp.get(this).onTrimMemory(level)
        super.onTrimMemory(level)
    }

    companion object {
        private const val VARIANT_DEV = "VARIANT_DEV"
    }
}
