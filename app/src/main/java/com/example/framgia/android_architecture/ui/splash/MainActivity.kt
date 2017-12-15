package com.example.framgia.android_architecture.ui.splash

import android.os.Bundle
import com.example.framgia.android_architecture.R
import com.example.framgia.android_architecture.base.ViewModelActivity
import kotlin.reflect.KClass

class MainActivity : ViewModelActivity<SplashViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onEvent() {
    }

    override fun viewModelClass(): KClass<SplashViewModel> = SplashViewModel::class

}
