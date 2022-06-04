package com.zeca.githubsample.remote.repositories.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.zeca.githubsample.data.repositories.models.Repository
import com.zeca.githubsample.remote.repositories.api.contracts.RepositoriesAPI
import com.zeca.githubsample.remote.repositories.api.responses.SearchRepositoriesResponse
import com.zeca.githubsample.remote.repositories.extensions.tryMap2APIException
import com.zeca.githubsample.remote.repositories.mappers.RepositoryMapper

private const val FIRST_PAGE = 1

class RemoteRepositoriesPaging(
    private val query: String,
    private val sort: String,
    private val mapper: RepositoryMapper,
    private val repositoriesAPI: RepositoriesAPI,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PagingSource<Int, Repository>() {

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        return try {
            return withContext(dispatcher) {
                val currentPage = params.key ?: FIRST_PAGE
                val response = repositoriesAPI.getRepositories(
                    query = query,
                    sort = sort,
                    page = currentPage,
                    perPage = params.loadSize
                )

                LoadResult.Page(
                    data = response.repositories.map { mapper.map(it) },
                    prevKey = evaluatePrevKey(currentPage),
                    nextKey = evaluateNextKey(response, currentPage, params.loadSize)
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

    private fun evaluatePrevKey(
        currentPage: Int
    ): Int? {
        return if (currentPage == FIRST_PAGE) null
        else currentPage - 1
    }

    private fun evaluateNextKey(
        response: SearchRepositoriesResponse,
        currentPage: Int,
        perPage: Int
    ): Int? {
        return if (endOfPaginationReached(response, perPage)) null
        else currentPage + 1
    }

    private fun endOfPaginationReached(
        response: SearchRepositoriesResponse,
        perPage: Int
    ): Boolean {
        return response.repositories.run {
            isEmpty() || size < perPage
        }
    }
}
