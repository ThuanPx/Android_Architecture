package com.example.framgia.architecture.features.home

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.framgia.architecture.R
import com.example.framgia.architecture.base.BaseFragment
import com.example.framgia.architecture.features.userdetail.UserDetailFragment
import com.example.framgia.architecture.utils.ktext.context.replaceFragmentToActivity
import com.example.framgia.architecture.utils.ktext.view.gone
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment<HomeViewModel>(HomeViewModel::class) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override val layoutID: Int
        get() = R.layout.home_fragment

    private var homeAdapter: HomeAdapter? = null

    override fun initialize() {
        etKeyWord.setText("ThuanPx")

        homeAdapter = HomeAdapter()
        homeAdapter?.setOnItemClickListener { user, position ->
            activity?.replaceFragmentToActivity(R.id.container, UserDetailFragment.newInstance())
        }

        rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            rvMain.adapter = homeAdapter
        }

        btSearch.setOnClickListener {
            viewModel.searchUser(etKeyWord.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        homeAdapter?.unRegisterItemClickListener()
    }

    override fun onSubscribeObserver() {
        viewModel.users.observe(this, Observer {
            homeAdapter?.submitData(it.toMutableList())
        })
        viewModel.onError.observe(this, Observer {
            handleApiError(it)
        })
        viewModel.isLoading.observe(this, Observer {
            loading.gone(!it)
        })
    }
}
