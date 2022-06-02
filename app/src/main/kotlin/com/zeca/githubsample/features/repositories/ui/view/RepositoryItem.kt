package com.zeca.githubsample.features.repositories.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.zeca.githubsample.features.repositories.ui.model.RepositoryUiModel

@Composable
fun RepositoryItem(repository: RepositoryUiModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 10.dp
    ) {
        ConstraintLayout {
            val (name, author, authorAvatar, starts, forks) = createRefs()

            Text(
                repository.name,
                fontSize = 24.sp,
                modifier = Modifier.constrainAs(name){})

            Text(repository.author,
                fontSize = 24.sp,
                modifier = Modifier.constrainAs(author) {
                    top.linkTo(name.bottom, margin = 16.dp)
                })

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(repository.authorAvatar)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.constrainAs(authorAvatar) {
                    start.linkTo(author.end, margin = 24.dp)
                    top.linkTo(author.top)
                }
            )

            Text(
                repository.stars.toString(),
                fontSize = 24.sp,
                modifier = Modifier.constrainAs(starts) {
                    top.linkTo(author.bottom, margin = 16.dp)
                })

            Text(
                repository.forks.toString(),
                fontSize = 24.sp,
                modifier = Modifier.constrainAs(forks) {
                    top.linkTo(starts.bottom, margin = 16.dp)
                })
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    RepositoryItem(
        RepositoryUiModel(
            1,
            "android",
            1000,
            2000,
            "Zeca",
            "url_image"
        )
    )
}