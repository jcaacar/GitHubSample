package com.zeca.githubsample.features.extensions

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import com.zeca.githubsample.R
import com.zeca.githubsample.common.exceptions.NetworkConnectionException
import com.zeca.githubsample.remote.repositories.api.exceptions.APIRateLimitExceeded

@RunWith(Parameterized::class)
class ExceptionExtensionsKtTest(
    private val throwable: Throwable,
    private val expectedResId: Int
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(NetworkConnectionException(), R.string.network_not_connected_msg),
            arrayOf(APIRateLimitExceeded(), R.string.api_rate_limit_exceed_msg),
            arrayOf(Exception(), R.string.general_error_msg)
        )
    }

    @Test
    fun `should returns correct error message`() {
        assertThat(throwable.getErrorResourceId()).isEqualTo(expectedResId)
    }
}
