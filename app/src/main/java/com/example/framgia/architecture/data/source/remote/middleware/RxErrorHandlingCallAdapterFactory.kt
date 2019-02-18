package com.example.framgia.architecture.data.source.remote.middleware

import android.text.TextUtils
import com.example.framgia.architecture.data.source.remote.error.ErrorResponse
import com.example.framgia.architecture.data.source.remote.error.RetrofitException
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class RxErrorHandlingCallAdapterFactory private constructor() : CallAdapter.Factory() {
    private val mInstance: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Suppress("UNCHECKED_CAST")
    override fun get(returnType: Type, annotations: Array<Annotation>,
                     retrofit: Retrofit): CallAdapter<*, *> {
        return RxCallAdapterWrapper(
            mInstance.get(returnType, annotations, retrofit) as CallAdapter<Any, Any>)
    }

    private class RxCallAdapterWrapper(
        private val wrapped: CallAdapter<Any, Any>) : CallAdapter<Any, Any> {
        override fun responseType(): Type {
            return wrapped.responseType()
        }

        override fun adapt(call: Call<Any>): Any {
            val result = wrapped.adapt(call)
            return when (result) {
                is Single<*> -> result.onErrorResumeNext { throwable -> Single.error(convertToBaseException(throwable)) }
                is Observable<*> -> result.onErrorResumeNext { throwable: Throwable ->
                    Observable.error(convertToBaseException(throwable))
                }
                is Completable -> result.onErrorResumeNext { throwable ->
                    Completable.error(convertToBaseException(throwable))
                }
                is Maybe<*> -> result.onErrorResumeNext { throwable: Throwable ->
                    Maybe.error(convertToBaseException(throwable))
                }
                else -> result
            }
        }

        private fun convertToBaseException(throwable: Throwable): RetrofitException {
            if (throwable is RetrofitException) {
                return throwable
            }

            // A network error happened
            if (throwable is IOException) {
                return RetrofitException.toNetworkError(throwable)
            }

            // We had non-200 http error
            if (throwable is HttpException) {
                val response = throwable.response()
                if (response.errorBody() != null) {
                    return try {
                        val errorResponse = Gson().fromJson(response.errorBody()?.string(),
                            ErrorResponse::class.java)

                        if (errorResponse != null && !TextUtils.isEmpty(errorResponse.message)) {
                            RetrofitException.toServerError(errorResponse)
                        } else {
                            RetrofitException.toHttpError(response)
                        }

                    } catch (e: IOException) {
                        RetrofitException.toUnexpectedError(throwable)
                    }
                } else {
                    return RetrofitException.toHttpError(response)
                }
            }

            // We don't know what happened. We need to simply convert to an unknown error
            return RetrofitException.toUnexpectedError(throwable)
        }
    }

    companion object {
        fun create(): CallAdapter.Factory {
            return RxErrorHandlingCallAdapterFactory()
        }
    }
}
