package com.zeca.githubsample.features.extensions

import com.zeca.githubsample.R
import com.zeca.githubsample.common.exceptions.NetworkConnectionException
import com.zeca.githubsample.remote.repositories.api.exceptions.APIRateLimitExceeded

fun Throwable.getErrorResourceId() : Int {
    return when (this) {
        is NetworkConnectionException -> R.string.network_not_connected_msg
        is APIRateLimitExceeded -> R.string.api_rate_limit_exceed_msg
        else -> R.string.general_error_msg
    }
}
