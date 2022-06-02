package com.zeca.githubsample.common.helpers

import javax.inject.Inject
import javax.inject.Singleton
import android.content.Context
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import com.zeca.githubsample.common.extensions.connectivityManager

@Singleton
class NetworkHandler @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.connectivityManager

        return connectivityManager.activeNetwork?.let { network ->
            return connectivityManager.getNetworkCapabilities(network)?.let { capabilities ->
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            } ?: false
        } ?: false
    }
}
