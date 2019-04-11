package com.example.framgia.architecture.data.source.repository

import com.example.framgia.architecture.data.model.User
import io.reactivex.Single

interface UserRepository {

    fun searchUser(keyword: String, page: Int = 1): Single<List<User>>
}