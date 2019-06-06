package com.example.framgia.android_architecture.di.module

import com.example.framgia.android_architecture.feature.blank.BlankFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * --------------------
 * Created by ThuanPx on 6/4/2019.
 * Screen name:
 * --------------------
 */
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeBlackFragment(): BlankFragment
}