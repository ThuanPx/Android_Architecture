package com.example.framgia.android_architecture.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * --------------------
 * Created by ThuanPx on 6/6/2019.
 * Screen name:
 * --------------------
 */
abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun launchRx(job: () -> Disposable) {
        compositeDisposable.addAll(job())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}