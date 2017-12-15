package com.example.framgia.android_architecture.data.source

import com.example.framgia.android_architecture.data.source.local.SharedPrefs
import com.example.framgia.android_architecture.data.source.remote.ArchitectureApi
import com.example.framgia.android_architecture.data.source.remote.middleware.AddHeaderInterceptor

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class Repository private constructor(private val mSharedPrefs: SharedPrefs,
    itc: AddHeaderInterceptor, private val mApi: ArchitectureApi) {

    init {
        itc.setRepository(this)
    }

    val accessToken: String get() = mSharedPrefs.accessToken

    fun clearSharedPrefs() = mSharedPrefs.clear()

    companion object {
        private var INSTANCE: Repository? = null

        fun getInstance(sharedPrefs: SharedPrefs, itc: AddHeaderInterceptor,
            api: ArchitectureApi): Repository =
            INSTANCE ?: Repository(sharedPrefs, itc, api).also { INSTANCE = it }
    }
}
