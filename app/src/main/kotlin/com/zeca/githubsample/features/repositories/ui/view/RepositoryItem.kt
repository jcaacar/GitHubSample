package com.zeca.githubsample.features.repositories.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeca.githubsample.features.repositories.ui.model.RepositoryUiModel

@Composable
fun RepositoryItem(repository: RepositoryUiModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 10.dp
    ) {
        Text(
            repository.name,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}
