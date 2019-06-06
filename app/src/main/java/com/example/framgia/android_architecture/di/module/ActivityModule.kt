package com.example.framgia.android_architecture.di.module

import com.example.framgia.android_architecture.feature.Home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * --------------------
 * Created by ThuanPx on 6/4/2019.
 * Screen name:
 * --------------------
 */
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): HomeActivity
}