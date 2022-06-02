package com.zeca.githubsample.remote.repositories.api.responses

import com.google.gson.annotations.SerializedName

data class SearchRepositoriesResponse(
    @SerializedName("items")
    val repositories: List<RepositoryResponse>
)
