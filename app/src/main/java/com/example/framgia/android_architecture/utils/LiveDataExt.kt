package com.example.framgia.android_architecture.utils

import android.arch.lifecycle.MutableLiveData

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
fun <T> MutableLiveData<T>.notifyValueChanged() { value = value }
