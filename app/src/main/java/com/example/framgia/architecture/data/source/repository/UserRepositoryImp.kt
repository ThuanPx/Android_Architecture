package com.example.framgia.architecture.data.source.repository

import com.example.framgia.architecture.data.model.User
import com.example.framgia.architecture.data.source.local.SharedPrefs
import com.example.framgia.architecture.data.source.remote.ArchitectureApi
import io.reactivex.Single

class UserRepositoryImp(private val sharedPrefs: SharedPrefs,
                        private val architectureApi: ArchitectureApi) : UserRepository {

    override fun searchUser(keyword: String, page: Int): Single<List<User>> {
        return architectureApi.searchUser(keyword, page).map { it.data }
    }


}