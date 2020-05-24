package com.example.framgia.architecture.di

import android.app.Application
import com.example.framgia.architecture.BuildConfig
import com.example.framgia.architecture.data.source.remote.ArchitectureApi
import com.example.framgia.architecture.data.source.remote.middleware.InterceptorImpl
import com.example.framgia.architecture.data.source.remote.middleware.RxErrorHandlingCallAdapterFactory
import com.example.framgia.architecture.data.source.repository.TokenRepository
import com.google.gson.Gson
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * --------------------
 * Created by ThuanPx on 6/17/2019.
 * Screen name:
 * --------------------
 */

val networkModule = module(createdAtStart = true) {
    single { provideOkHttpCache(get()) }
    single { provideInterceptor(get()) }
    single { provideOkHttpClient(get(), get()) }
    single { provideRetrofit(get(), get()) }
    single { provideApi(get()) }
}

fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpCache(app: Application): Cache {
    val cacheSize: Long = 10 * 1024 * 1024 // 10 MiB
    return Cache(app.cacheDir, cacheSize)
}

fun provideInterceptor(tokenRepository: TokenRepository): Interceptor {
    return InterceptorImpl(tokenRepository)
}

fun provideOkHttpClient(cache: Cache, interceptor: Interceptor): OkHttpClient {
    val httpClientBuilder = OkHttpClient.Builder()
    httpClientBuilder.cache(cache)
    httpClientBuilder.addInterceptor(interceptor)

    httpClientBuilder.readTimeout(
        NetworkConstants.READ_TIMEOUT, TimeUnit.SECONDS
    )
    httpClientBuilder.writeTimeout(
        NetworkConstants.WRITE_TIMEOUT, TimeUnit.SECONDS
    )
    httpClientBuilder.connectTimeout(
        NetworkConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS
    )

    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        httpClientBuilder.addInterceptor(logging)
        logging.level = HttpLoggingInterceptor.Level.BODY
    }

    return httpClientBuilder.build()
}

fun provideApi(retrofit: Retrofit): ArchitectureApi {
    return retrofit.create(ArchitectureApi::class.java)
}

object NetworkConstants {
    const val READ_TIMEOUT: Long = 30
    const val WRITE_TIMEOUT: Long = 30
    const val CONNECTION_TIMEOUT: Long = 30
}