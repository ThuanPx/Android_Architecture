package com.example.framgia.android_architecture.data.source.repository

import com.example.framgia.android_architecture.data.model.Repo
import com.example.framgia.android_architecture.data.source.remote.GithubAPI
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * --------------------
 * Created by ThuanPx on 6/6/2019.
 * Screen name:
 * --------------------
 */

interface RepoRepository {
    fun searchRepo(keyWord: String, page: Int): Single<List<Repo>>
}

@Singleton
class RepoRepositoryImpl @Inject constructor(private val api: GithubAPI) : RepoRepository {

    override fun searchRepo(keyWord: String, page: Int): Single<List<Repo>> {
        return api.searchRepo(keyWord, page).map { it.items }
    }

}