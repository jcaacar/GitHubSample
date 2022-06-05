package com.zeca.githubsample.di

import kotlinx.coroutines.delay
import com.zeca.githubsample.helpers.RepositoriesAPIResponseHelper
import com.zeca.githubsample.remote.netwok.retrofit.apis.RetrofitRepositoriesAPI
import com.zeca.githubsample.remote.repositories.api.responses.SearchRepositoriesResponse

private const val LOADING_DELAY = 2000L

class RetrofitRepositoriesAPIStub : RetrofitRepositoriesAPI {
    var isError = false
    var isLoading = false

    override suspend fun getRepositories(
        query: String,
        sort: String,
        page: Int,
        perPage: Int
    ): SearchRepositoriesResponse {
        if (isError) throw Exception()

        if (isLoading) {
            delay(LOADING_DELAY)
            return SearchRepositoriesResponse(listOf())
        }

        return RepositoriesAPIResponseHelper.getHttpOkResponse()
    }
}
