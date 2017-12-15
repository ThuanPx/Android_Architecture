package com.example.framgia.android_architecture.ui

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.framgia.android_architecture.Injection
import com.example.framgia.android_architecture.data.source.Repository
import com.example.framgia.android_architecture.ui.splash.SplashViewModel

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class ViewModelFactory(private val application: Application,
    private val repository: Repository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel(application)
                else -> throw IllegalArgumentException("Unknown ViewModel: ${modelClass.name}")
            }
        } as T

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java){
                INSTANCE ?: ViewModelFactory(application, Injection.provideRepository(
                    application.applicationContext)).also { INSTANCE = it }
            }
    }
}
