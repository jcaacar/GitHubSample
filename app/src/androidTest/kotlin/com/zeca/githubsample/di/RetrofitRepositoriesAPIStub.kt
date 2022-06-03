package com.zeca.githubsample.di

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zeca.githubsample.network.retrofit.apis.RetrofitRepositoriesAPI
import com.zeca.githubsample.remote.repositories.api.responses.SearchRepositoriesResponse

class RetrofitRepositoriesAPIStub : RetrofitRepositoriesAPI {

    override suspend fun getRepositories(
        query: String,
        sort: String,
        page: Int,
        perPage: Int
    ): SearchRepositoriesResponse {
        val response = RetrofitRepositoriesAPIStub::class.java.classLoader!!
            .getResource(RESPONSE_OK)!!
            .readText()
        val typeToken = object : TypeToken<SearchRepositoriesResponse>() {}.type
        return Gson().fromJson(response, typeToken)
    }

    companion object {
        private const val RESPONSE_OK = "github_http_ok.json"
    }
}
