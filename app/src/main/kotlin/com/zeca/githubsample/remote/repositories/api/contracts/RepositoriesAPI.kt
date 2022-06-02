package com.zeca.githubsample.remote.repositories.api.contracts

import com.zeca.githubsample.remote.repositories.api.responses.SearchRepositoriesResponse

interface RepositoriesAPI {

    suspend fun getRepositories(
        query: String,
        sort: String,
        page: Int,
        perPage: Int
    ): SearchRepositoriesResponse

}
