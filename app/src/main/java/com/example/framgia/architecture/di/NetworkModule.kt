package com.example.framgia.architecture.di

import android.content.Context
import com.example.framgia.architecture.BuildConfig
import com.example.framgia.architecture.data.source.remote.ArchitectureApi
import com.example.framgia.architecture.data.source.remote.middleware.AddHeaderInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * Created by ThuanPx on 1/25/19.
 *
 */
val NetworkModule = module {

    single { provideGson() }

    single { provideAddHeaderInterceptor(androidApplication()) }

    single { provideOkHttpClient(get()) }

    single { provideApi(get(), get()) }

}

object NetworkConstants {
    internal const val END_POINT = "https://api.github.com/"
    internal const val CONNECTION_TIMEOUT = 15
}

fun provideGson(): Gson = GsonBuilder().create()

fun provideAddHeaderInterceptor(context: Context) = AddHeaderInterceptor(context)

fun provideOkHttpClient(interceptor: AddHeaderInterceptor): OkHttpClient {
    val httpClientBuilder = OkHttpClient.Builder()
        .readTimeout(NetworkConstants.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        .connectTimeout(NetworkConstants.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        .addInterceptor(interceptor)
    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        httpClientBuilder.addInterceptor(logging)
        logging.level = HttpLoggingInterceptor.Level.BODY
    }
    return httpClientBuilder.build()
}


fun provideApi(client: OkHttpClient, gson: Gson): ArchitectureApi =
    Retrofit.Builder().baseUrl(NetworkConstants.END_POINT)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build().create(ArchitectureApi::class.java)