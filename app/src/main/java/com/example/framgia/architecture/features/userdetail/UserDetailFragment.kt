package com.example.framgia.architecture.features.userdetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.framgia.architecture.R

class UserDetailFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() = UserDetailFragment()
    }

    private lateinit var viewModel: UserDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.user_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
