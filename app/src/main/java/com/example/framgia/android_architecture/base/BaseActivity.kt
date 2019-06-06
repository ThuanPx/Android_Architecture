package com.example.framgia.android_architecture.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.framgia.android_architecture.data.source.remote.error.RetrofitException
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * --------------------
 * Created by ThuanPx on 6/6/2019.
 * Screen name:
 * --------------------
 */
abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    protected abstract val viewModelClass: Class<T>

    protected val viewModel: T by lazy { ViewModelProviders.of(this, viewModelFactory).get(viewModelClass) }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSubscribeObserver()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        init()
    }

    fun onError(error: RetrofitException) {

    }

    protected abstract fun onSubscribeObserver()
    protected abstract fun init()
}