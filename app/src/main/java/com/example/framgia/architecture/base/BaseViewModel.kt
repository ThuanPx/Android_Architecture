package com.example.framgia.architecture.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framgia.architecture.utils.liveData.SingleLiveEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 *
 * Created by ThuanPx on 1/25/19.
 *
 */
abstract class BaseViewModel : ViewModel() {

    val isLoading = SingleLiveEvent<Boolean>()
    val onError = SingleLiveEvent<Throwable>()

    fun showLoading(isShow: Boolean = true) {
        isLoading.value = isShow
    }

    fun setError(error: Throwable?) {
        onError.value = error
    }

    fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                isLoading.value = true
                block()
            } finally {
                isLoading.value = false
            }
        }
    }
}
