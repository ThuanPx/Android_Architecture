package com.example.framgia.architecture.data.model

import com.google.gson.annotations.SerializedName

data class User(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url") val avatarUrl: String
)
