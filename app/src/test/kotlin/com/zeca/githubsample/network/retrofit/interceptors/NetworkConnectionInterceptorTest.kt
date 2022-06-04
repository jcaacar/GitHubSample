package com.zeca.githubsample.network.retrofit.interceptors

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import okhttp3.Interceptor
import org.junit.Test
import com.zeca.githubsample.common.exceptions.NetworkConnectionException
import com.zeca.githubsample.common.helpers.NetworkHandler

class NetworkConnectionInterceptorTest {

    @Test
    fun `should continue the chain calls`() {
        val chainMock = buildChainMock()
        val networkHandlerMock = buildNetworkHandlerSpy(true)

        NetworkConnectionInterceptor(networkHandlerMock)
            .intercept(chainMock)

        verify(exactly = 1) { chainMock.request() }
        verify(exactly = 1) { chainMock.proceed(any()) }
    }

    @Test(expected = NetworkConnectionException::class)
    fun `should throws NetworkConnectionException`() {
        val chainMock = buildChainMock()
        val networkHandlerMock = buildNetworkHandlerSpy(false)

        NetworkConnectionInterceptor(networkHandlerMock)
            .intercept(chainMock)
    }

    private fun buildChainMock(): Interceptor.Chain {
        return mockk<Interceptor.Chain>().also {
            every { it.proceed(any()) } returns mockk()
            every { it.request() } returns mockk()
        }
    }

    private fun buildNetworkHandlerSpy(isNetworkAvailable: Boolean): NetworkHandler {
        return spyk(NetworkHandler(mockk())).also {
            every { it.isNetworkAvailable() } returns isNetworkAvailable
        }
    }
}
