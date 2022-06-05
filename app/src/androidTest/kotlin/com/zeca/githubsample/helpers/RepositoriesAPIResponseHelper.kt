package com.zeca.githubsample.helpers

import com.google.gson.Gson
import com.zeca.githubsample.remote.repositories.api.responses.SearchRepositoriesResponse

private const val REPOSITORIES_HTTP_OK = "github_http_ok.json"

object RepositoriesAPIResponseHelper {

    fun getHttpOkResponse(): SearchRepositoriesResponse {
        return this::class.java.classLoader!!
            .getResource(REPOSITORIES_HTTP_OK)!!.run {
                Gson().fromJson(readText(), SearchRepositoriesResponse::class.java)
            }
    }
}
