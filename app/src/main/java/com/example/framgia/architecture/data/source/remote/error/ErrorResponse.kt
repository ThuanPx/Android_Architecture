package com.example.framgia.architecture.data.source.remote.error

import com.example.framgia.architecture.utils.ktext.string.nullToEmpty
import java.io.IOException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber

/**
 * Created by ThuanPx on 2/18/19.
 */
data class ErrorResponse(private val response: Response<*>?) {

    var code: Int = 0

    var message: String? = null

    init {
        response?.let {
            code = it.code()
            Timber.e("errorCode: $code")
            try {
                val errorJson = JSONObject(it.errorBody()?.string().nullToEmpty()).toString()
                val errorMsg = JSONObject(errorJson).getString(ERROR_MESSAGE_PARAM)
                message = errorMsg
                Timber.e("errorMsg: $errorMsg")
            } catch (e: JSONException) {
                Timber.e(e.localizedMessage)
            } catch (e: IOException) {
                Timber.e(e.localizedMessage)
            }
        }
    }

    companion object {
        private const val ERROR_MESSAGE_PARAM = "message"
    }
}
