package com.zeca.githubsample.remote.repositories.api.responses

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    val id: Int,
    val name: String,
    @SerializedName("stargazers_count")
    val stars: Int,
    @SerializedName("forks_count")
    val forks: Int,
    val owner: OwnerResponse
)
