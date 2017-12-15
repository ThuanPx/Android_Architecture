package com.example.framgia.android_architecture.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.support.annotation.StringRes
import com.example.framgia.android_architecture.App
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
abstract class BaseViewModel(context: Application) : AndroidViewModel(context) {
    internal var disposable: CompositeDisposable? = null

    protected fun rx(block: () -> Disposable?) {
        if (disposable == null) throw IllegalArgumentException(
            "No worry! You've just forgot inject disposable, DO IT!")
        block()?.let { disposable?.add(it) }
    }

    protected fun getString(@StringRes res: Int) = getApplication<App>().getString(res) ?: ""
}
