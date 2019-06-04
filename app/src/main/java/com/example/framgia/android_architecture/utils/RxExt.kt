package com.example.framgia.android_architecture.utils

import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
fun Completable.async() =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun Completable.loading(liveData: MutableLiveData<Boolean>) =
    doOnSubscribe { liveData.value = true }.doFinally { liveData.value = false }

fun Completable.loading(block: (Boolean) -> Unit) =
    doOnSubscribe { block(true) }.doFinally { block(false) }

fun <T> Single<T>.async() =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.loading(liveData: MutableLiveData<Boolean>) =
    doOnSubscribe { liveData.value = true }.doFinally { liveData.value = false }

fun <T> Single<T>.loading(block: (Boolean) -> Unit) =
    doOnSubscribe { block(true) }.doFinally { block(false) }
