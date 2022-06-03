package com.zeca.githubsample.remote.repositories.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.zeca.githubsample.data.repositories.models.Repository
import com.zeca.githubsample.remote.repositories.api.contracts.RepositoriesAPI
import com.zeca.githubsample.remote.repositories.extensions.tryMap2APIException
import com.zeca.githubsample.remote.repositories.mappers.RepositoryMapper

private const val FIRST_PAGE = 1

internal class RemoteRepositoryPaging(
    private val query: String,
    private val sort: String,
    private val mapper: RepositoryMapper,
    private val repositoriesAPI: RepositoriesAPI
) : PagingSource<Int, Repository>() {

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        return try {
            return withContext(Dispatchers.IO) {
                val currentPage = params.key ?: FIRST_PAGE
                val response = repositoriesAPI.getRepositories(
                    query = query,
                    sort = sort,
                    page = currentPage,
                    perPage = params.loadSize
                )

                val prevKey = if (currentPage == FIRST_PAGE) null else currentPage - 1
                val nextKey = if (response.repositories.isEmpty()) null else currentPage + 1

                LoadResult.Page(
                    data = response.repositories.map { mapper.map(it) },
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
        } catch (e: Exception) {
            loadErrorResult(e)
        }
    }

    private fun loadErrorResult(exception: Exception): LoadResult.Error<Int, Repository> {
        return exception.tryMap2APIException()?.let {
            LoadResult.Error(it)
        } ?: LoadResult.Error(exception)
    }
}
