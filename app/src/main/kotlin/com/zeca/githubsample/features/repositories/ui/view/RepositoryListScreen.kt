package com.zeca.githubsample.features.repositories.ui.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zeca.githubsample.features.repositories.RepositoriesViewModel

@Composable
fun RepositoryListScreen(viewModel: RepositoriesViewModel = viewModel()) {
    RepositoryList(viewModel.repositories)
}
