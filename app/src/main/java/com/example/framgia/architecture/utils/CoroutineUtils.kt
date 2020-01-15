package com.example.framgia.architecture.utils

import com.example.framgia.architecture.base.Result
import java.io.IOException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

object CoroutineUtils {
    suspend fun <T> safeApiCall(
            dispatcher: CoroutineDispatcher = Dispatchers.IO,
            apiCall: suspend () -> T
    ): Result<T> {
        return withContext(dispatcher) {
            try {
                Result.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> Result.NetworkError
                    is HttpException -> {
                        Result.Error(throwable.code(), throwable)
                    }
                    else -> {
                        Result.Error(null, null)
                    }
                }
            }
        }
    }
}
