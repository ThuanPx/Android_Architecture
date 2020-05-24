package com.example.framgia.architecture.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.example.framgia.architecture.utils.SingleLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Created by ThuanPx on 1/25/19.
 */
abstract class BaseViewModel : ViewModel() {

    private var compositeDisposable = CompositeDisposable()
    val isLoading = SingleLiveData<Boolean>()
    val onError = SingleLiveData<Throwable>()

    protected fun Disposable.addToDisposable() {
        compositeDisposable.add(this)
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
