package com.example.framgia.architecture.features.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.framgia.architecture.R
import com.example.framgia.architecture.base.EndlessRecyclerViewScrollListener
import com.example.framgia.architecture.features.HomeAdapter
import com.example.framgia.architecture.features.userdetail.UserDetailFragment
import com.example.framgia.architecture.utils.SafeObserver
import com.example.framgia.architecture.utils.goTo
import com.example.framgia.architecture.utils.gone
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModel()
    private val homeAdapter by lazy {
        HomeAdapter {
            activity?.goTo(UserDetailFragment.newInstance(), R.id.container)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        etKeyWord.setText("ThuanPx")

        val layoutManager = LinearLayoutManager(context)
        rvMain.layoutManager = layoutManager
        rvMain.adapter = homeAdapter

        rvMain.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.searchUser(etKeyWord.text.toString(), page)
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

        viewModel.usersLiveData.observe(this, SafeObserver {
            homeAdapter.setData(it)
        })
        viewModel.onError.observe(this, SafeObserver {
            Log.e("Error", it.localizedMessage)
        })
        viewModel.isLoading.observe(this, SafeObserver {
            loading.gone(!it)
        })
    }

}
