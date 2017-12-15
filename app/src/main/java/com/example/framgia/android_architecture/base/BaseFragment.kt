package com.example.framgia.android_architecture.base

import android.os.Bundle
import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
abstract class BaseFragment : Fragment() {
    internal val disposable = CompositeDisposable()
    protected abstract fun onEvent()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
