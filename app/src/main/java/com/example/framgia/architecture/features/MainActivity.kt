package com.example.framgia.architecture.features

import com.example.framgia.architecture.R
import com.example.framgia.architecture.base.BaseActivity
import com.example.framgia.architecture.features.home.HomeFragment
import com.example.framgia.architecture.utils.ktext.context.replaceFragmentToActivity

class MainActivity : BaseActivity<MainViewModel>(MainViewModel::class) {

    override val layoutID: Int
        get() = R.layout.main_activity

    override fun initialize() {
        replaceFragmentToActivity(R.id.container, HomeFragment.newInstance())
    }

    override fun onSubscribeObserver() {
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStackImmediate()
        else
            super.onBackPressed()
    }
}
