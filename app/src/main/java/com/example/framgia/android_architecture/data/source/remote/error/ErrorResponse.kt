package com.example.framgia.android_architecture.data.source.remote.error

import com.example.framgia.android_architecture.Constants
import com.example.framgia.android_architecture.utils.extensions.toStringWithFormatPattern
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ErrorResponse {

  @Expose
  @SerializedName("error")
  private val mError: Error? = null

  val error: Error
    get() = mError ?: Error()

  inner class Error {
    @Expose
    @SerializedName("code")
    val code: Int = 0
    @Expose
    @SerializedName("description")
    private val messages: List<String>? = null

    val message: String?
      get() = if (messages == null || messages.isEmpty()) {
        ""
      } else {
        messages.toStringWithFormatPattern(Constants.ENTER_SPACE_FORMAT)
      }
  }
}
