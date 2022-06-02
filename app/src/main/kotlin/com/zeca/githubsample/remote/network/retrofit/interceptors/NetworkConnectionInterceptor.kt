package com.zeca.githubsample.remote.network.retrofit.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import com.zeca.githubsample.common.exceptions.NetworkConnectionException

internal class NetworkConnectionInterceptor(
    private val isNetworkConnected: () -> Boolean
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkConnected()) {
            throw NetworkConnectionException()
        }
        return chain.proceed(chain.request())
    }
}
