package com.zeca.githubsample.features.repositories.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kotlinx.coroutines.flow.Flow
import com.zeca.githubsample.features.repositories.ui.model.RepositoryUiModel

@Composable
fun RepositoryList(repositories: Flow<PagingData<RepositoryUiModel>>) {
    val items = repositories.collectAsLazyPagingItems()

    LazyColumn {
        items(
            items = items,
            key = { it.id }) { repo ->
            repo?.let {
                RepositoryItem(repository = it)
            }
        }
    }
}
