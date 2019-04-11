package com.example.framgia.architecture.features.home

import com.example.framgia.architecture.base.BaseViewModel
import com.example.framgia.architecture.data.model.User
import com.example.framgia.architecture.data.source.repository.UserRepository
import com.example.framgia.architecture.utils.rx.SingleLiveData
import com.example.framgia.architecture.utils.rx.async
import com.example.framgia.architecture.utils.rx.loading
import com.example.framgia.architecture.utils.schedulerprovider.SchedulerProvider

class HomeViewModel(private val userRepository: UserRepository,
                    private val schedulerProvider: SchedulerProvider) : BaseViewModel() {

    val usersLiveData by lazy { SingleLiveData<List<User>>() }
    val users by lazy { mutableListOf<User>() }

    fun searchUser(keyWord: String, page: Int = 1) = rx {
        userRepository.searchUser(keyWord, page)
            .async(schedulerProvider)
            .loading(isLoading)
            .subscribe({ users.addAll(it);  usersLiveData.value = users }, { onError.value = it })
    }

}