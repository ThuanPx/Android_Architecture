package com.example.framgia.architecture.base

import androidx.lifecycle.ViewModel
import com.example.framgia.architecture.utils.liveData.SingleLiveData

/**
 *
 * Created by ThuanPx on 1/25/19.
 *
 */
abstract class BaseViewModel : ViewModel() {

    val isLoading = SingleLiveData<Boolean>()
    val onError = SingleLiveData<Throwable>()

    fun showLoading(isShow: Boolean = true) {
        isLoading.value = isShow
    }

    fun setError(error: Throwable) {
        onError.value = error
    }
}
