package com.example.framgia.architecture.features.home

import com.example.framgia.architecture.base.BaseViewModel
import com.example.framgia.architecture.data.model.User
import com.example.framgia.architecture.data.source.remote.error.RetrofitException
import com.example.framgia.architecture.data.source.repository.UserRepository
import com.example.framgia.architecture.utils.rx.SingleLiveData
import com.example.framgia.architecture.utils.rx.async
import com.example.framgia.architecture.utils.rx.loading
import com.example.framgia.architecture.utils.schedulerprovider.SchedulerProvider

class HomeViewModel(private val userRepository: UserRepository,
                    private val schedulerProvider: SchedulerProvider) : BaseViewModel() {

    val users by lazy { SingleLiveData<MutableList<User>>() }

    fun searchUser(keyWord: String) = rx {
        userRepository.searchUser(keyWord)
            .async(schedulerProvider)
            .loading(isLoading)
            .subscribe({ users.value = it as MutableList<User> }, { onError.value = it as RetrofitException })
    }

}