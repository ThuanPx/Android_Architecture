package com.example.framgia.architecture.di

import android.app.Application
import com.example.framgia.architecture.data.source.local.sharedprf.SharedPrefs
import com.example.framgia.architecture.data.source.local.sharedprf.SharedPrefsImpl
import com.example.framgia.architecture.data.source.remote.ArchitectureApi
import com.example.framgia.architecture.data.source.repository.UserRepository
import com.example.framgia.architecture.data.source.repository.UserRepositoryImp
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 *
 * Created by ThuanPx on 1/25/19.
 *
 */

val RepositoryModule = module {
    single { provideSharedPrefsApi(androidApplication()) }

    single { provideUserRepository(get(), get()) }
}


fun provideSharedPrefsApi(app: Application): SharedPrefs {
    return SharedPrefsImpl(app)
}

fun provideUserRepository(sharedPrefs: SharedPrefs, architectureApi: ArchitectureApi): UserRepository {
    return UserRepositoryImp(sharedPrefs, architectureApi)
}