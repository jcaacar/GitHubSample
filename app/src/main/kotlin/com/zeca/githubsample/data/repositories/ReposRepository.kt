package com.zeca.githubsample.data.repositories

import javax.inject.Inject
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.zeca.githubsample.data.repositories.contracts.RepositoriesDataSource
import com.zeca.githubsample.data.repositories.models.Repository

private const val ITEMS_PER_PAGE = 15
private const val INITIAL_LOAD_SIZE = 50
private const val QUERY_KOTLIN_REPOS = "language:kotlin"
private const val SORT_BY_STARTS = "stars"

class RepoRepository @Inject constructor(
    private val remoteDataSource: RepositoriesDataSource
) {
    fun getKotlinRepositories(): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = INITIAL_LOAD_SIZE,
                pageSize = ITEMS_PER_PAGE
            ),
            pagingSourceFactory = {
                remoteDataSource.getRepositories(
                    QUERY_KOTLIN_REPOS,
                    SORT_BY_STARTS
                )
            }
        ).flow
    }
}
