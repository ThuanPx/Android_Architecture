package com.example.framgia.android_architecture.base

import android.arch.lifecycle.AndroidViewModel
import android.os.Bundle
import android.view.View
import com.example.framgia.android_architecture.utils.obtainViewModel
import kotlin.reflect.KClass

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
abstract class ViewModelFragment<T : AndroidViewModel> : BaseFragment() {
    protected lateinit var mViewModel: T
    protected abstract var shareViewModel: Boolean
    abstract fun viewModelClass(): KClass<T>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = obtainViewModel(viewModelClass().java, shareViewModel)
    }
}
