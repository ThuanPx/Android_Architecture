package com.example.framgia.android_architecture.data.source.remote.middleware

import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class AddHeaderInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request()
            .newBuilder()
        //TODO add header here
        val response  = chain.proceed(builder.build())
        if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(
                Intent(UnauthorizedBroadcast.INTENT_UNAUTHORIZED))
        }
        return response
    }

}
