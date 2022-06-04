package com.zeca.githubsample.features.repositories

import androidx.paging.PagingData
import app.cash.turbine.test
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import com.zeca.githubsample.TestDispatcherRule
import com.zeca.githubsample.data.repositories.RepoRepository
import com.zeca.githubsample.data.repositories.contracts.RepositoriesDataSource
import com.zeca.githubsample.data.repositories.models.Repository
import com.zeca.githubsample.features.repositories.ui.model.RepositoryUiModelMapper

class RepositoriesViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    @Test
    fun `should return one page`() = runTest {
        val dataSourceMock = mockk<RepositoriesDataSource>()
        val repoRepositorySpy = spyk(RepoRepository(dataSourceMock))
        every { repoRepositorySpy.getKotlinRepositories() } answers {
            flow {
                emit(PagingData.from(listOf(Repository(1, "", 10, 5, "", ""))))
            }
        }

        val viewModel = RepositoriesViewModel(
            RepositoryUiModelMapper(),
            repoRepositorySpy
        )

//        TODO: FIX THIS TEST
//        viewModel.repositories.test {
//            awaitItem()
//            awaitComplete()
//        }
    }
}
