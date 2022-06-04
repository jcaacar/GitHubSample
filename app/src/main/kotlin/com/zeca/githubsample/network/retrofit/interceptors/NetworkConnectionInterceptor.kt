package com.zeca.githubsample.network.retrofit.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import com.zeca.githubsample.common.helpers.NetworkHandler

internal class NetworkConnectionInterceptor(
    private val networkHandler: NetworkHandler
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        networkHandler.ensureNetworkAvailable()
        return chain.proceed(chain.request())
    }
}
