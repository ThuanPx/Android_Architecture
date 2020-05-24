package com.example.framgia.architecture.di

import com.example.framgia.architecture.features.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * --------------------
 * Created by ThuanPx on 6/17/2019.
 * --------------------
 */

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
}
