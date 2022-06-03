package com.zeca.githubsample.features.repositories.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.zeca.githubsample.features.repositories.ui.model.RepositoryUiModel

@Composable
fun RepositoryList(items: LazyPagingItems<RepositoryUiModel>) {
    LazyColumn(
        modifier = Modifier.testTag(RepositoryListTestTags.LIST_TAG)
    ) {
        itemsIndexed(items) { index, repo ->
            RepositoryCard(repo!!, index)
        }
    }
}

object RepositoryListTestTags {
    const val LIST_TAG = "LIST_TAG"
}
