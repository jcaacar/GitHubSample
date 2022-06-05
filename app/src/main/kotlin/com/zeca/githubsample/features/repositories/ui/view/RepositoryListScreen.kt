package com.zeca.githubsample.features.repositories.ui.view

import androidx.annotation.StringRes
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.zeca.githubsample.design.components.Loading
import com.zeca.githubsample.features.extensions.getErrorResourceId
import com.zeca.githubsample.features.repositories.RepositoriesViewModel

@Composable
fun RepositoryListScreen(viewModel: RepositoriesViewModel = viewModel()) {
    val items = viewModel.repositories.collectAsLazyPagingItems()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            RepositoryList(items)

            items.apply {
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        Loading()
                    }
                    else -> {
                        val errorState = (loadState.append as? LoadState.Error)
                            ?: loadState.refresh as? LoadState.Error

                        errorState?.run {
                            SnackBarError(
                                error.getErrorResourceId(),
                                scaffoldState
                            )
                        }
                    }
                }
            }
        })
}

@Composable
private fun SnackBarError(
    @StringRes resId: Int,
    scaffoldState: ScaffoldState
) {
    val msg = stringResource(id = resId)
    LaunchedEffect(scaffoldState.snackbarHostState) {
        scaffoldState.snackbarHostState.showSnackbar(
            msg,
            duration = SnackbarDuration.Short
        )
    }
}
