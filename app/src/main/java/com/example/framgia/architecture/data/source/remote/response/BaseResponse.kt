package com.example.framgia.architecture.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class BaseResponse<T>(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("items") val data: T
)
