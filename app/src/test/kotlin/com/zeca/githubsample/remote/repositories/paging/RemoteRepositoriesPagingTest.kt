package com.zeca.githubsample.remote.repositories.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test
import com.zeca.githubsample.common.extensions.takeMap
import com.zeca.githubsample.data.repositories.models.Repository
import com.zeca.githubsample.remote.repositories.RepositoryResponseHelper
import com.zeca.githubsample.remote.repositories.api.responses.RepositoryResponse
import com.zeca.githubsample.remote.repositories.mappers.RepositoryMapper

class RemoteRepositoriesPagingTest {

    @Test
    fun `should returns always anchorPosition when refresh key`() {
        val responseApi = RepositoriesAPIStub()
        val repositoriesPaging = RemoteRepositoriesPaging("", "", RepositoryMapper(), responseApi)

        val anchorPosition = 10

        val result = repositoriesPaging.getRefreshKey(
            state = PagingState(
                listOf(),
                anchorPosition = anchorPosition,
                config = PagingConfig(10),
                0
            )
        )

        assertThat(result).isEqualTo(anchorPosition)
    }

    @Test
    fun `should returns repositories paginated with success`() = runTest {
        val response = RepositoryResponseHelper.build(5)

        val page = 1
        val perPage = 5

        val expectedLoadResult =
            PagingSource.LoadResult.Page(
                data = response.takeMap(perPage, RepositoryMapper()),
                prevKey = null,
                nextKey = 2
            )

        runLoadTest(expectedLoadResult, response, page, perPage)
    }

    @Test
    fun `should returns empty when doesn't pagination is ended`() = runTest {
        val response = RepositoryResponseHelper.build(5)

        val page = 2
        val perPage = 5

        val expectedLoadResult =
            PagingSource.LoadResult.Page(
                data = listOf<Repository>(),
                prevKey = 1,
                nextKey = null
            )

        runLoadTest(expectedLoadResult, response, page, perPage)
    }

    @Test
    fun `should returns an error when exception is thrown`() = runTest {
        val error = Exception("error")
        val expectedLoadResult = PagingSource.LoadResult.Error<Int, Repository>(error)

        runLoadTest(expectedLoadResult, error = error)
    }

    private suspend fun runLoadTest(
        expectedLoadResult: PagingSource.LoadResult<Int, Repository>,
        responseApi: List<RepositoryResponse> = listOf(),
        page: Int? = null,
        perPage: Int = 5,
        error: Throwable? = null
    ) {
        val repositoryMapper = RepositoryMapper()
        val repositoriesApi = RepositoriesAPIStub(responseApi, error)

        val repositoryPaging =
            RemoteRepositoriesPaging(
                "",
                "",
                repositoryMapper,
                repositoriesApi
            )

        val actualResult = repositoryPaging.load(
            PagingSource.LoadParams.Refresh(
                key = page,
                loadSize = perPage,
                placeholdersEnabled = false
            )
        )

        if (error != null) {
            val result = actualResult as PagingSource.LoadResult.Error
            assertThat(result.throwable.message).isEqualTo(error.message)
        } else {
            assertThat(actualResult).isEqualTo(expectedLoadResult)
        }
    }
}
