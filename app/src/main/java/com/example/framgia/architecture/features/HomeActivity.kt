package com.example.framgia.architecture.features

import com.example.framgia.architecture.R
import com.example.framgia.architecture.base.BaseActivity
import com.example.framgia.architecture.features.home.HomeFragment
import com.example.framgia.architecture.features.home.HomeViewModel

class HomeActivity: BaseActivity<HomeViewModel>(HomeViewModel::class) {

    override val layoutID: Int
        get() = R.layout.home_activity

    override fun initialize() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
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
