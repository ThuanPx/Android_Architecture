package com.example.framgia.android_architecture.di

import android.app.Application
import com.example.framgia.android_architecture.App
import com.example.framgia.android_architecture.di.module.ActivityModule
import com.example.framgia.android_architecture.di.module.AppModule
import com.example.framgia.android_architecture.di.module.NetworkModule
import com.example.framgia.android_architecture.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * --------------------
 * Created by ThuanPx on 6/4/2019.
 * Screen name:
 * --------------------
 */
@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            AppModule::class,
            NetworkModule::class,
            RepositoryModule::class,
            ActivityModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}