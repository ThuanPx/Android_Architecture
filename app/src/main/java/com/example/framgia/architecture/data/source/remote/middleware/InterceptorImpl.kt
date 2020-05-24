package com.example.framgia.architecture.data.source.remote.middleware

import androidx.annotation.NonNull
import com.example.framgia.architecture.data.source.repository.TokenRepository
import com.example.framgia.architecture.utils.ktext.string.insert
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber

class InterceptorImpl(private var tokenRepository: TokenRepository?) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {
        val builder = initializeHeader(chain)
        val request = builder.build()

        return chain.proceed(request)
    }

    private fun initializeHeader(chain: Interceptor.Chain): Request.Builder {
        val originRequest = chain.request()

        val urlWithPrefix = "BuildConfig.BASE_URL"
        val newUrl = chain.request().url.toString().insert(
            index = urlWithPrefix.length,
            contentInsert = ""
        )
        Timber.e("newUrl $newUrl")
        val builder = originRequest.newBuilder()
            .url(newUrl)
            .header("Accept", "application/json")
            .addHeader("Cache-Control", "no-cache")
            .addHeader("Cache-Control", "no-store")
            .method(originRequest.method, originRequest.body)

        tokenRepository?.getToken()?.accessToken?.let { accessToken ->
            builder.addHeader(KEY_TOKEN, TOKEN_TYPE + accessToken)
        }

        return builder
    }

    companion object {
        private const val TOKEN_TYPE = "Bearer "
        private const val KEY_TOKEN = "Authorization"
    }
}
