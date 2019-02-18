package com.example.framgia.architecture.di

import com.example.framgia.architecture.features.home.HomeViewModel
import com.example.framgia.architecture.features.userdetail.UserDetailViewModel
import com.example.framgia.architecture.utils.schedulerprovider.SchedulerProvider
import com.example.framgia.architecture.utils.schedulerprovider.SchedulerProviderImp
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 *
 * Created by ThuanPx on 1/25/19.
 *
 */

val AppModule = module {

    viewModel { HomeViewModel(get(), get()) }

    viewModel { UserDetailViewModel() }

    single<SchedulerProvider>(createOnStart = true) { SchedulerProviderImp() }
}

val rootModule = listOf(AppModule, NetworkModule, RepositoryModule)
