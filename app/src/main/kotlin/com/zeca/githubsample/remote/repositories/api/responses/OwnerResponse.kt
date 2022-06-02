package com.zeca.githubsample.remote.repositories.api.responses

import com.google.gson.annotations.SerializedName

data class OwnerResponse(
    val id: Int,
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)
