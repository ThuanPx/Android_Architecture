package com.example.framgia.architecture.base

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val code: Int? = null, val throwable: Throwable? = null) : Result<Nothing>()
    object NetworkError : Result<Nothing>()
}

