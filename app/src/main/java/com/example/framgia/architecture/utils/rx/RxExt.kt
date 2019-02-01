package com.example.framgia.architecture.utils.rx

import android.arch.lifecycle.MutableLiveData
import com.example.framgia.architecture.utils.schedulerprovider.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
fun Completable.async(schedulerProvider: SchedulerProvider) =
    subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())

fun Completable.loading(liveData: MutableLiveData<Boolean>) =
    doOnSubscribe { liveData.value = true }.doFinally { liveData.value = false }

fun Completable.loading(block: (Boolean) -> Unit) =
    doOnSubscribe { block(true) }.doFinally { block(false) }

fun <T> Single<T>.async(schedulerProvider: SchedulerProvider) =
    subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())

fun <T> Single<T>.loading(liveData: MutableLiveData<Boolean>) =
    doOnSubscribe { liveData.value = true }.doFinally { liveData.value = false }

fun <T> Single<T>.loading(block: (Boolean) -> Unit) =
    doOnSubscribe { block(true) }.doFinally { block(false) }
