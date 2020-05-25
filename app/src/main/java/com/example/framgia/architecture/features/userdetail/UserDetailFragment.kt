package com.example.framgia.architecture.features.userdetail

import com.example.framgia.architecture.R
import com.example.framgia.architecture.base.BaseFragment
import com.example.framgia.architecture.features.home.HomeViewModel

class UserDetailFragment : BaseFragment<HomeViewModel>(HomeViewModel::class) {
    override val layoutID: Int
        get() = R.layout.user_detail_fragment

    override fun initialize() {
        // TODO init
    }

    override fun onSubscribeObserver() {
        // TODO subscribe live data
    }

    companion object {
        fun newInstance() = UserDetailFragment()
    }
}
