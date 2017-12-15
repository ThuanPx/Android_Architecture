package com.example.framgia.android_architecture.utils

import android.arch.lifecycle.Observer

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class SafeObserver<T>(private val mNotifier: (T) -> Unit) : Observer<T> {
    override fun onChanged(t: T?) {
        t?.let { mNotifier(t) }
    }
}
