package com.example.framgia.architecture.data.source.remote.error

import com.example.framgia.architecture.R
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class ApiException(throwable: Throwable?, var messageStringId: Int? = null) : Throwable() {
    override val message get() = _message
    val execTime: String get() = _execTime
    val errorCode: ErrorCode get() = _errorCode

    private var _message: String = ""
    private var _execTime: String = ""
    private var _errorCode: ErrorCode = ErrorCode.UNEXPECTED_EXCEPTION

    init {
        when (throwable) {
            is HttpException -> httpError(throwable)
            is IOException -> networkError()
            null -> {
            }
            else -> unexpectedError(throwable)
        }
    }

    constructor(message: String, execTime: String = "") : this(null) {
        _message = message
        _execTime = execTime
    }

    private fun httpError(error: HttpException) {
        val errorBody = JSONObject(error.response().errorBody()?.string() ?: "{}")
        _message = errorBody.getString(ERROR_KEY)
        _execTime = errorBody.getString(EXEC_TIME_KEY)
        _errorCode = ErrorCode.HTTP_EXCEPTION
    }

    private fun networkError() {
        //TODO edit message error
        messageStringId = R.string.app_name
        _errorCode = ErrorCode.NETWORK_EXCEPTION
    }

    private fun unexpectedError(error: Throwable) {
        //TODO edit message error
        messageStringId = R.string.app_name
        _errorCode = ErrorCode.UNEXPECTED_EXCEPTION
        error.printStackTrace()
    }

    companion object {
        val ERROR_KEY = "error"
        val EXEC_TIME_KEY = "exectime"
        val NetworkException = ApiException(IOException())
    }
}
