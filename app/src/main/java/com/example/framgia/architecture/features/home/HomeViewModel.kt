package com.example.framgia.architecture.features.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.framgia.architecture.base.BaseViewModel
import com.example.framgia.architecture.base.Result
import com.example.framgia.architecture.data.model.User
import com.example.framgia.architecture.data.source.repository.UserRepository
import com.example.framgia.architecture.utils.rx.SingleLiveData
import com.example.framgia.architecture.utils.schedulerprovider.SchedulerProvider
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
                if (result is Result.Success) {
                    users.addAll(result.data.data)
                    usersLiveData.value = users
                } else {
                    Log.e("Home View Model", (result as Result.Error).exception.localizedMessage)
                }
            }
        }
    }
}