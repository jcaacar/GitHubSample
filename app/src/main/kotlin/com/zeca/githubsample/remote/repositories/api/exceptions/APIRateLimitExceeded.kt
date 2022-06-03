package com.zeca.githubsample.remote.repositories.api.exceptions

class APIRateLimitExceeded : APIException() {
    companion object {
        private const val expectedMessage = "HTTP 403"

        fun hasMatch(ex: Exception): Boolean {
            return ex.localizedMessage?.contains(expectedMessage) == true
        }
    }
}
