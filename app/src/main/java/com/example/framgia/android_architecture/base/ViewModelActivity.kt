package com.example.framgia.android_architecture.base

import android.arch.lifecycle.AndroidViewModel
import android.os.Bundle
import com.example.framgia.android_architecture.utils.obtainViewModel
import kotlin.reflect.KClass

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
abstract class ViewModelActivity<T : AndroidViewModel> : BaseActivity() {
    protected lateinit var mViewModel: T
    abstract fun viewModelClass(): KClass<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = obtainViewModel(viewModelClass().java)
    }
}
