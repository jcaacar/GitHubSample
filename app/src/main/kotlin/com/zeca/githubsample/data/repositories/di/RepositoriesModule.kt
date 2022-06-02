package com.zeca.githubsample.data.repositories.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.zeca.githubsample.data.repositories.RepoRepository
import com.zeca.githubsample.data.repositories.contracts.RepositoriesDataSource

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    fun provideGitHubRepository(repositoriesDataSource: RepositoriesDataSource) =
        RepoRepository(repositoriesDataSource)
}
