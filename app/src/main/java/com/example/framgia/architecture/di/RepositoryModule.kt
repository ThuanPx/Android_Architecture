package com.example.framgia.architecture.di

import android.content.Context
import com.example.framgia.architecture.data.source.local.SharedPrefs
import com.example.framgia.architecture.data.source.repository.UserRepository
import com.example.framgia.architecture.data.source.repository.UserRepositoryImp
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

/**
 *
 * Created by ThuanPx on 1/25/19.
 *
 */

val RepositoryModule = module {
    single { provideSharedPrefs(androidApplication()) }

    single<UserRepository> { UserRepositoryImp(get(), get()) }
}

fun provideSharedPrefs(context: Context): SharedPrefs = SharedPrefs(context)