package com.example.framgia.architecture.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Token(
    @Expose
    @SerializedName("access_token")
    val accessToken: String? = null,
    @Expose
    @SerializedName("expires_at")
    val expiresAt: String? = null
)
