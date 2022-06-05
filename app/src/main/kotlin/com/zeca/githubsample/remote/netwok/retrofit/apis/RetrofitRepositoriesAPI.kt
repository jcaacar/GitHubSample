package com.zeca.githubsample.remote.netwok.retrofit.apis

import retrofit2.http.GET
import retrofit2.http.Query
import com.zeca.githubsample.remote.repositories.api.contracts.RepositoriesAPI
import com.zeca.githubsample.remote.repositories.api.responses.SearchRepositoriesResponse

internal interface RetrofitRepositoriesAPI : RepositoriesAPI {

    @GET("search/repositories")
    override suspend fun getRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): SearchRepositoriesResponse
}
