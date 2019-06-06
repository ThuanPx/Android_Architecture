package com.example.framgia.android_architecture.di.module

import com.example.framgia.android_architecture.data.source.remote.GithubAPI
import com.example.framgia.android_architecture.data.source.repository.RepoRepository
import com.example.framgia.android_architecture.data.source.repository.RepoRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * --------------------
 * Created by ThuanPx on 6/6/2019.
 * Screen name:
 * --------------------
 */

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepoRepository(api: GithubAPI): RepoRepository = RepoRepositoryImpl(api)
}