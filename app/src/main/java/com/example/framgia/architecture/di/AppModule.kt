package com.example.framgia.architecture.di

import com.example.framgia.architecture.features.home.HomeViewModel
import com.example.framgia.architecture.features.userdetail.UserDetailViewModel
import com.example.framgia.architecture.utils.schedulerprovider.SchedulerProviderImp
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *
 * Created by ThuanPx on 1/25/19.
 *
 */

val AppModule = module {

    viewModel { HomeViewModel(get()) }

    single { SchedulerProviderImp() }
}

val rootModule = listOf(AppModule, NetworkModule, RepositoryModule)
