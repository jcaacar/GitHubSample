package com.zeca.githubsample.data.repositories.contracts

import androidx.paging.PagingSource
import com.zeca.githubsample.data.repositories.models.Repository

interface RepositoriesDataSource {
    fun getRepositories(
        query: String,
        sort: String
    ): PagingSource<Int, Repository>
}
