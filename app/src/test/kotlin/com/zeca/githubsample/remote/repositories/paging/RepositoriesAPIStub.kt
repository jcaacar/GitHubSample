package com.zeca.githubsample.remote.repositories.paging

import com.zeca.githubsample.remote.repositories.api.contracts.RepositoriesAPI
import com.zeca.githubsample.remote.repositories.api.responses.RepositoryResponse
import com.zeca.githubsample.remote.repositories.api.responses.SearchRepositoriesResponse

class RepositoriesAPIStub(
    private val repositories: List<RepositoryResponse> = listOf(),
    private val error: Throwable? = null
) : RepositoriesAPI {

    override suspend fun getRepositories(
        query: String,
        sort: String,
        page: Int,
        perPage: Int
    ): SearchRepositoriesResponse {

        error?.let {
            throw it
        }

        val starIndex = (page - 1) * perPage
        val endIndex = starIndex + perPage

        if (starIndex >= repositories.size || endIndex > repositories.size) {
            return SearchRepositoriesResponse(listOf())
        }

        val pageResult = repositories.subList(starIndex, endIndex)
        return SearchRepositoriesResponse(pageResult)
    }
}
