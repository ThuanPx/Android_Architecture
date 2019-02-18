package com.example.framgia.architecture.features.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.framgia.architecture.R
import com.example.framgia.architecture.features.HomeAdapter
import com.example.framgia.architecture.features.userdetail.UserDetailFragment
import com.example.framgia.architecture.utils.SafeObserver
import com.example.framgia.architecture.utils.goTo
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

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

        rvMain.layoutManager = LinearLayoutManager(context)
        rvMain.adapter = homeAdapter
        btSearch.setOnClickListener {
            viewModel.searchUser(etKeyWord.text.toString())
        }
        viewModel.users.observe(this, SafeObserver {
            homeAdapter.set(it)
        })
        viewModel.onError.observe(this, SafeObserver {
            Log.i("test", it.getMessageError())
        })
    }

}
