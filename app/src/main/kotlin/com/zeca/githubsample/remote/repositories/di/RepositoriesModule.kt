package com.zeca.githubsample.remote.repositories.di

import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.zeca.githubsample.data.repositories.contracts.RepositoriesDataSource
import com.zeca.githubsample.remote.repositories.RemoteRepositoriesDataSource
import com.zeca.githubsample.remote.repositories.api.contracts.RepositoriesAPI
import com.zeca.githubsample.remote.repositories.mappers.RepositoryMapper

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoriesModule {

    @Singleton
    @Provides
    fun provideRemoteRepositoriesDataSource(repositoriesAPI: RepositoriesAPI) =
        RemoteRepositoriesDataSource(RepositoryMapper(), repositoriesAPI) as RepositoriesDataSource
}
