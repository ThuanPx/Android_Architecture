package com.example.framgia.architecture.data.source.repository

import com.example.framgia.architecture.data.model.User
import com.example.framgia.architecture.data.source.local.sharedprf.SharedPrefs
import com.example.framgia.architecture.data.source.remote.ArchitectureApi
import io.reactivex.rxjava3.core.Single

interface ApiRepository {
    fun searchUser(keyword: String, page: Int = 1): Single<List<User>>
}

class ApiRepositoryImp(
    private val sharedPrefs: SharedPrefs,
    private val architectureApi: ArchitectureApi
) : ApiRepository {

    override fun searchUser(keyword: String, page: Int): Single<List<User>> {
        return architectureApi.searchUser(keyword, page).map { it.data }
    }
}
