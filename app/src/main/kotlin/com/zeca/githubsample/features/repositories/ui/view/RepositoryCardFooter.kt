package com.zeca.githubsample.features.repositories.ui.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zeca.githubsample.R
import com.zeca.githubsample.design.components.IconText
import com.zeca.githubsample.features.repositories.ui.model.RepositoryUiModel

@Composable
fun RepositoryCardFooter(repository: RepositoryUiModel, position: Int) {
    Row(
        modifier = Modifier.padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconText(
            resId = R.drawable.ic_baseline_star_border_24,
            text = repository.stars.toString(),
            textTag = RepositoryItemTestTags.STARS.plus(position)
        )

        Spacer(Modifier.padding(12.dp))

        IconText(
            resId = R.drawable.ic_baseline_account_tree_24,
            text = repository.forks.toString(),
            textTag = RepositoryItemTestTags.FORKS.plus(position)
        )
    }
}
