package com.zeca.githubsample.remote.network.retrofit.di

import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.zeca.githubsample.BuildConfig
import com.zeca.githubsample.common.helpers.NetworkHandler
import com.zeca.githubsample.remote.network.retrofit.apis.RetrofitRepositoriesAPI
import com.zeca.githubsample.remote.network.retrofit.interceptors.NetworkConnectionInterceptor
import com.zeca.githubsample.remote.repositories.api.contracts.RepositoriesAPI

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideGitHubBaseUrl() = BuildConfig.GITHUB_API_BASE_URL

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        networkHandler: NetworkHandler,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor)
        }

        builder.addInterceptor(
            NetworkConnectionInterceptor(
                networkHandler::isNetworkAvailable
            )
        )

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRepositoriesAPI(okHttpClient: OkHttpClient, gitHubBaseUrl: String): RepositoriesAPI {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(gitHubBaseUrl)
            .client(okHttpClient)
            .build()

        return retrofit.create(RetrofitRepositoriesAPI::class.java)
    }
}
