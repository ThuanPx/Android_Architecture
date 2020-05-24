package com.example.framgia.architecture.data.source.remote

import com.example.framgia.architecture.data.model.User
import com.example.framgia.architecture.data.source.remote.response.BaseResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
interface ArchitectureApi {
    @GET("/search/users")
    fun searchUser(@Query("q") keyword: String, @Query("page") page: Int): Single<BaseResponse<List<User>>>
}
