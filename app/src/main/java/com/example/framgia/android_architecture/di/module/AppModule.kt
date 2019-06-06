package com.example.framgia.android_architecture.di.module

import com.example.framgia.android_architecture.utils.schedulers.SchedulerProvider
import com.example.framgia.android_architecture.utils.schedulers.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * --------------------
 * Created by ThuanPx on 6/4/2019.
 * Screen name:
 * --------------------
 */
@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideScheduler(): SchedulerProvider = SchedulerProviderImpl()
}