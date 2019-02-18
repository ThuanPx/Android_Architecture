package com.example.framgia.architecture.base

import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import com.example.framgia.architecture.data.source.remote.error.RetrofitException
import com.example.framgia.architecture.utils.rx.SingleLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 *
 * Created by ThuanPx on 1/25/19.
 *
 */
abstract class BaseViewModel : ViewModel() {

    private var disposable = CompositeDisposable()
    val isLoading = SingleLiveData<Boolean>()
    val onError = SingleLiveData<RetrofitException>()

    protected fun rx(block: () -> Disposable?) {
        block()?.let { disposable.add(it) }
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
