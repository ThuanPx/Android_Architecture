package com.example.framgia.android_architecture.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
abstract class BaseActivity : AppCompatActivity() {
    internal val disposable = CompositeDisposable()
    protected abstract fun onEvent()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        onEvent()
    }

    override fun onStop() {
        disposable.clear()
        super.onStop()
    }

    protected fun rx(block: () -> Disposable) {
        disposable.add(block())
    }
}
