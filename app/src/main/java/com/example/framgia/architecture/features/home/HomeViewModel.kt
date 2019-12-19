package com.example.framgia.architecture.features.home

import androidx.lifecycle.viewModelScope
import com.example.framgia.architecture.base.BaseViewModel
import com.example.framgia.architecture.base.Result
import com.example.framgia.architecture.data.model.User
import com.example.framgia.architecture.data.source.repository.UserRepository
import com.example.framgia.architecture.utils.liveData.SingleLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    val usersLiveData by lazy { SingleLiveData<List<User>>() }
    val users by lazy { mutableListOf<User>() }

    fun searchUser(keyWord: String, page: Int = 1) {
        viewModelScope.launch {
            showLoading()
            userRepository.searchUser(keyWord, page).let { result ->
                showLoading(false)
                when(result) {
                    is Result.Success -> {
                        withContext(Dispatchers.Default) {
                            result.data.data.forEach {
                                users.add(it)
                            }
                        }
                        usersLiveData.value = users
                    }
                    is Result.Error -> {
                        setError(result.exception)
                    }
                }
            }
        }
    }
}