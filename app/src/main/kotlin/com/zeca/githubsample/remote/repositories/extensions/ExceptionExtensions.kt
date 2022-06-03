package com.zeca.githubsample.remote.repositories.extensions

import com.zeca.githubsample.remote.repositories.api.exceptions.APIException
import com.zeca.githubsample.remote.repositories.api.exceptions.APIRateLimitExceeded

fun Exception.tryMap2APIException(): APIException? {
    return if (APIRateLimitExceeded.hasMatch(this)) {
        APIRateLimitExceeded()
    } else {
        null
    }
}
