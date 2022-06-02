package com.zeca.githubsample.remote.repositories

import androidx.paging.PagingSource
import com.zeca.githubsample.data.repositories.contracts.RepositoriesDataSource
import com.zeca.githubsample.data.repositories.models.Repository
import com.zeca.githubsample.remote.repositories.api.contracts.RepositoriesAPI
import com.zeca.githubsample.remote.repositories.mappers.RepositoryMapper
import com.zeca.githubsample.remote.repositories.paging.RemoteRepositoryPaging

internal class RemoteRepositoriesDataSource(
    private val mapper: RepositoryMapper,
    private val repositoriesAPI: RepositoriesAPI
) : RepositoriesDataSource {
    override fun getRepositories(
        query: String,
        sort: String
    ): PagingSource<Int, Repository> {
        return RemoteRepositoryPaging(query, sort, mapper, repositoriesAPI)
    }
}
