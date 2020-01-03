package com.example.framgia.architecture.features.home

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.framgia.architecture.R
import com.example.framgia.architecture.base.BaseFragment
import com.example.framgia.architecture.base.recyclerView.EndlessRecyclerOnScrollListener
import com.example.framgia.architecture.features.HomeAdapter
import com.example.framgia.architecture.features.userdetail.UserDetailFragment
import com.example.framgia.architecture.utils.extension.replaceFragmentInActivity
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment<HomeViewModel>(HomeViewModel::class) {

    override val layoutID: Int
        get() = R.layout.home_fragment

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val homeAdapter by lazy {
        HomeAdapter {
            activity?.replaceFragmentInActivity(R.id.container, UserDetailFragment.newInstance())
        }
    }


    override fun initialize() {
        etKeyWord.setText("ThuanPx")

        val layoutManager = LinearLayoutManager(context)
        rvMain.layoutManager = layoutManager
        rvMain.adapter = homeAdapter

        rvMain.addOnScrollListener(object : EndlessRecyclerOnScrollListener(layoutManager) {
            override fun onLoadMore(currentPage: Int) {
                viewModel.searchUser(etKeyWord.text.toString(), currentPage)
            }
        })


        btSearch.setOnClickListener {
            viewModel.searchUser(etKeyWord.text.toString())
        }
        btDelete.setOnClickListener {
            homeAdapter.setData(viewModel.users.apply {
                removeAt(0)
            })
        }
    }

    override fun onSubscribeObserver() {
        viewModel.usersLiveData.observe(this, Observer {
            homeAdapter.setData(it)
        })
        viewModel.onError.observe(this, Observer {
            handleApiError(it)
        })
        viewModel.isLoading.observe(this, Observer {
            showLoading(it)
        })
    }

}
