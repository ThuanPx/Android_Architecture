package com.example.framgia.android_architecture.di.module

import android.app.Application
import com.example.framgia.android_architecture.BuildConfig
import com.example.framgia.android_architecture.data.source.remote.GithubAPI
import com.example.framgia.android_architecture.data.source.remote.middleware.AddHeaderInterceptor
import com.example.framgia.android_architecture.data.source.remote.middleware.RxErrorHandlingCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * --------------------
 * Created by ThuanPx on 6/6/2019.
 * Screen name:
 * --------------------
 */
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideGithubService(okHttpClient: OkHttpClient): GithubAPI {
        return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(GithubAPI::class.java)
    }


    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor {
        return AddHeaderInterceptor()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(cache: Cache, interceptor: Interceptor): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.cache(cache)
        httpClientBuilder.addInterceptor(interceptor)

        httpClientBuilder.readTimeout(
                READ_TIMEOUT, TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(
                WRITE_TIMEOUT, TimeUnit.SECONDS)
        httpClientBuilder.connectTimeout(
                CONNECTION_TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            httpClientBuilder.addInterceptor(logging)
            logging.level = HttpLoggingInterceptor.Level.BODY
        }

        return httpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideOkHttpCache(app: Application): Cache {
        val cacheSize: Long = 10 * 1024 * 1024 // 10 MiB
        return Cache(app.cacheDir, cacheSize)
    }

    companion object {
        private const val READ_TIMEOUT: Long = 30
        private const val WRITE_TIMEOUT: Long = 30
        private const val CONNECTION_TIMEOUT: Long = 30
    }
}