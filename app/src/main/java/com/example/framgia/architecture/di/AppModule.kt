package com.example.framgia.architecture.di

import android.app.Application
import android.content.res.Resources
import com.example.framgia.architecture.data.source.local.sharedprf.SharedPrefs
import com.example.framgia.architecture.data.source.local.sharedprf.SharedPrefsImpl
import com.example.framgia.architecture.data.source.remote.middleware.BooleanAdapter
import com.example.framgia.architecture.data.source.remote.middleware.DoubleAdapter
import com.example.framgia.architecture.data.source.remote.middleware.IntegerAdapter
import com.example.framgia.architecture.utils.schedulerprovider.SchedulerProvider
import com.example.framgia.architecture.utils.schedulerprovider.SchedulerProviderImp
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module

/**
 * --------------------
 * Created by ThuanPx on 6/17/2019.
 * --------------------
 */

val appModule = module(createdAtStart = true) {
    single { provideResources(get()) }
    single { provideSharedPrefs(get())}
    single { provideSchedulerProvider() }
    single { provideGson() }
}

fun provideResources(app: Application): Resources {
    return app.resources
}

fun provideSharedPrefs(app: Application): SharedPrefs {
    return SharedPrefsImpl(app)
}

fun provideSchedulerProvider(): SchedulerProvider {
    return SchedulerProviderImp()
}

fun provideGson(): Gson {
    val booleanAdapter = BooleanAdapter()
    val integerAdapter = IntegerAdapter()
    val doubleAdapter = DoubleAdapter()
    return GsonBuilder()
        .registerTypeAdapter(Boolean::class.java, booleanAdapter)
        .registerTypeAdapter(Int::class.java, integerAdapter)
        .registerTypeAdapter(Double::class.java, doubleAdapter)
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
}
