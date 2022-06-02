package com.zeca.githubsample.features.repositories.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.zeca.githubsample.R
import com.zeca.githubsample.common.exceptions.NetworkConnectionException
import com.zeca.githubsample.features.repositories.ui.model.RepositoryUiModel

@Composable
fun RepositoryList(repositories: Flow<PagingData<RepositoryUiModel>>) {
    val networkErrorMsg = stringResource(R.string.network_not_connected_msg)

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val items = repositories.collectAsLazyPagingItems()

    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            LazyColumn {
                items(
                    items = items,
                    key = { it.id }) { repo ->
                    RepositoryItem(repo!!)
                }

                items.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> this@LazyColumn.item { CircularProgressIndicator() }
                        loadState.append is LoadState.Loading -> this@LazyColumn.item { CircularProgressIndicator() }
                        loadState.append is LoadState.Error -> {
                            val e = items.loadState.append as LoadState.Error
                            if (e.error is NetworkConnectionException) {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(networkErrorMsg, duration = SnackbarDuration.Short)
                                }
                            } else {
                                this@LazyColumn.item {
                                    Text(e.error.localizedMessage!!)
                                }
                            }
                        }
                        loadState.refresh is LoadState.Error -> {
                            val e = items.loadState.refresh as LoadState.Error
                            val msg = when (e.error) {
                                is NetworkConnectionException -> networkErrorMsg
                                else -> e.error.localizedMessage!!
                            }
                            this@LazyColumn.item {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(msg, duration = SnackbarDuration.Short)
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}
