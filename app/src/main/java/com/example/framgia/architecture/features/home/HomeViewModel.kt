package com.example.framgia.architecture.features.home

import com.example.framgia.architecture.base.BaseViewModel
import com.example.framgia.architecture.data.model.User
import com.example.framgia.architecture.data.source.repository.ApiRepository
import com.example.framgia.architecture.utils.SingleLiveData
import com.example.framgia.architecture.utils.ktext.rxjava.async
import com.example.framgia.architecture.utils.ktext.rxjava.loading
import com.example.framgia.architecture.utils.schedulerprovider.SchedulerProvider

class HomeViewModel(
    private val apiRepository: ApiRepository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val users = SingleLiveData<List<User>>()

    fun searchUser(keyWord: String, page: Int = 1) {
        apiRepository.searchUser(keyWord, page)
            .loading(isLoading)
            .async(schedulerProvider)
            .subscribe(
                { users.value = it },
                { onError.value = it }
            )
            .addToDisposable()
    }
}
