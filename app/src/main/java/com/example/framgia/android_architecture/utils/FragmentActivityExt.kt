package com.example.framgia.android_architecture.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import com.example.framgia.android_architecture.base.BaseActivity
import com.example.framgia.android_architecture.base.BaseViewModel
import com.example.framgia.android_architecture.base.ViewModelActivity
import com.example.framgia.android_architecture.base.ViewModelFragment
import com.example.framgia.android_architecture.ui.ViewModelFactory

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
fun <T : ViewModel> ViewModelActivity<*>.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this, ViewModelFactory.getInstance(application))
        .get(viewModelClass).also { (it as BaseViewModel).disposable = this.disposable }

fun <T : ViewModel> ViewModelFragment<*>.obtainViewModel(viewModelClass: Class<T>,
    share: Boolean): T = activity!!.let {
    (if (share) {
        ViewModelProviders.of(it, ViewModelFactory.getInstance(it.application)).get(viewModelClass)
    } else {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(it.application))
            .get(viewModelClass)
    }).also {
        val disposable = if (share) (activity as BaseActivity).disposable else this.disposable
        (it as BaseViewModel).disposable = disposable
    }
}
