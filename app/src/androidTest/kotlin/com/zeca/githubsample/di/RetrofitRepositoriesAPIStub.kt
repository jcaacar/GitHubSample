package com.zeca.githubsample.di

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import com.zeca.githubsample.network.retrofit.apis.RetrofitRepositoriesAPI
import com.zeca.githubsample.remote.repositories.api.responses.SearchRepositoriesResponse

private const val RESPONSE_OK = "github_http_ok.json"

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
        if (isLoading) delay(200)

        val response = RetrofitRepositoriesAPIStub::class.java.classLoader!!
            .getResource(RESPONSE_OK)!!
            .readText()
        val typeToken = object : TypeToken<SearchRepositoriesResponse>() {}.type
        return Gson().fromJson(response, typeToken)
    }
}
