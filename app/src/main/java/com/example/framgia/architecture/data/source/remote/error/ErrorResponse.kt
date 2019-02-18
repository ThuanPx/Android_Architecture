package com.example.framgia.architecture.data.source.remote.error

import com.google.gson.annotations.SerializedName

/**
 *
 * Created by ThuanPx on 2/18/19.
 *
 */
//TODO: edit when known base error
data class ErrorResponse(@SerializedName("message") val message: String)