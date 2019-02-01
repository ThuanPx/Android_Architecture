package com.example.framgia.architecture.data.source.remote.middleware

import com.example.framgia.architecture.data.source.remote.error.ApiException
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
                is Single<*> -> result.onErrorResumeNext { t -> Single.error(ApiException(t)) }
                is Observable<*> -> result.onErrorResumeNext { throwable: Throwable ->
                    Observable.error(ApiException(throwable))
                }
                is Completable -> result.onErrorResumeNext { t ->
                    Completable.error(ApiException(t))
                }
                is Maybe<*> -> result.onErrorResumeNext { t: Throwable ->
                    Maybe.error(ApiException(t))
                }
                else -> result
            }
        }
    }

    companion object {
        fun create(): CallAdapter.Factory {
            return RxErrorHandlingCallAdapterFactory()
        }
    }
}
