package com.example.framgia.architecture.di

import com.example.framgia.architecture.data.source.local.sharedprf.SharedPrefs
import com.example.framgia.architecture.data.source.remote.ArchitectureApi
import com.example.framgia.architecture.data.source.repository.ApiRepository
import com.example.framgia.architecture.data.source.repository.ApiRepositoryImp
import com.example.framgia.architecture.data.source.repository.TokenRepository
import com.example.framgia.architecture.data.source.repository.TokenRepositoryImpl
import org.koin.dsl.module

/**
 * Created by ThuanPx on 1/25/19.
 */

val repositoryModule = module {
    single { provideTokenRepository(get()) }
    single { provideApiRepository(get(), get()) }
}

fun provideTokenRepository(sharedPrefs: SharedPrefs): TokenRepository {
    return TokenRepositoryImpl(sharedPrefs)
}

fun provideApiRepository(sharedPrefs: SharedPrefs, architectureApi: ArchitectureApi): ApiRepository {
    return ApiRepositoryImp(sharedPrefs, architectureApi)
}
