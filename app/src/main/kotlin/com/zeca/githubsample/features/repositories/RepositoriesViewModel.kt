package com.zeca.githubsample.features.repositories

import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import com.zeca.githubsample.data.repositories.RepoRepository
import com.zeca.githubsample.features.repositories.ui.model.RepositoryUiModel
import com.zeca.githubsample.features.repositories.ui.model.RepositoryUiModelMapper

private const val TIMEOUT_FLOW_UNSUBSCRIBE = 5000L

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    private val mapper: RepositoryUiModelMapper,
    private val gitHubRepository: RepoRepository
) : ViewModel() {

    val repositories: Flow<PagingData<RepositoryUiModel>> = flow {
        emitAll(gitHubRepository.getKotlinRepositories().map { page ->
            page.map { repo ->
                mapper.map(repo)
            }
        })
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(TIMEOUT_FLOW_UNSUBSCRIBE),
        PagingData.empty()
    ).cachedIn(viewModelScope)
}
