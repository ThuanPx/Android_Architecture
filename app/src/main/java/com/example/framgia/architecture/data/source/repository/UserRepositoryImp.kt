package com.example.framgia.architecture.data.source.repository

import com.example.framgia.architecture.base.Result
import com.example.framgia.architecture.data.model.User
import com.example.framgia.architecture.data.source.local.sharedprf.SharedPrefs
import com.example.framgia.architecture.data.source.remote.ArchitectureApi
import com.example.framgia.architecture.data.source.remote.response.BaseResponse
import com.example.framgia.architecture.utils.CoroutineUtils

interface UserRepository {

    suspend fun searchUser(keyword: String, page: Int = 1): Result<BaseResponse<List<User>>>
}

class UserRepositoryImp(private val sharedPrefs: SharedPrefs,
                        private val architectureApi: ArchitectureApi) : UserRepository {


    override suspend fun searchUser(keyword: String, page: Int): Result<BaseResponse<List<User>>> = CoroutineUtils.safeApiCall() {
        architectureApi.searchUserAsync(keyword, page)
    }

}