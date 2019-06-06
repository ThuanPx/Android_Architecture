package com.example.framgia.android_architecture.data.source.remote

import com.example.framgia.android_architecture.data.source.response.RepoSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * --------------------
 * Created by ThuanPx on 6/4/2019.
 * Screen name:
 * --------------------
 */
interface GithubAPI {
    @GET("search/users")
    fun searchRepo(@Query("q") keyWord: String, @Query("page") page: Int): Single<RepoSearchResponse>
}