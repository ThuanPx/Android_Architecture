package com.example.framgia.android_architecture.feature.Home

import android.os.Bundle
import com.example.framgia.android_architecture.R
import com.example.framgia.android_architecture.base.BaseActivity

class HomeActivity : BaseActivity<HomeViewModel>() {

    override val viewModelClass: Class<HomeViewModel> = HomeViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewModel.searchRepo("ThuanPx",1)
    }


    override fun init() {

    }

    override fun onSubscribeObserver() {

    }
}
