package com.example.framgia.android_architecture

import android.content.Context
import com.example.framgia.android_architecture.data.source.Repository
import com.example.framgia.android_architecture.data.source.local.SharedPrefs
import com.example.framgia.android_architecture.data.source.remote.ArchitectureApi
import com.example.framgia.android_architecture.data.source.remote.middleware.AddHeaderInterceptor
import com.example.framgia.android_architecture.data.source.remote.middleware.RxErrorHandlingCallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
object Injection {
    fun provideRepository(context: Context): Repository {
        val interceptor = provideAddHeaderInterceptor(context)
        return Repository.getInstance(provideSharedPrefs(context), interceptor,
            provideApi(provideOkHttpClient(
                interceptor), provideGson()))
    }

    fun provideSharedPrefs(context: Context): SharedPrefs = SharedPrefs(context)

    fun provideGson() = GsonBuilder().create()

    fun provideAddHeaderInterceptor(context: Context) = AddHeaderInterceptor(context)

    fun provideOkHttpClient(interceptor: AddHeaderInterceptor): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
            .readTimeout(Constants.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(Constants.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(interceptor)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            httpClientBuilder.addInterceptor(logging)
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        return httpClientBuilder.build()
    }

    //TODO edit url here
    fun provideApi(client: OkHttpClient, gson: Gson): ArchitectureApi =
        Retrofit.Builder().baseUrl("https://www.google.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
            .client(client)
            .build().create(ArchitectureApi::class.java)

}
