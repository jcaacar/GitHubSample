package com.zeca.githubsample.features.repositories.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeca.githubsample.design.components.RoundedLargeImage
import com.zeca.githubsample.features.repositories.ui.model.RepositoryUiModel
import com.zeca.githubsample.features.repositories.ui.view.RepositoryItemTestTags.AUTHOR
import com.zeca.githubsample.features.repositories.ui.view.RepositoryItemTestTags.AUTHOR_IMAGE
import com.zeca.githubsample.features.repositories.ui.view.RepositoryItemTestTags.NAME

@Composable
fun RepositoryCard(repository: RepositoryUiModel, position: Int) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        elevation = 12.dp
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colors.surface)
        ) {

            RoundedLargeImage(
                url = repository.authorAvatar,
                testTag = AUTHOR_IMAGE.plus(position)
            )

            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = repository.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.Black,
                    modifier = Modifier.testTag(NAME + position)
                )
                Text(
                    text = repository.author,
                    style = typography.body2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(end = 24.dp)
                        .testTag(AUTHOR + position)
                )

                RepositoryCardFooter(
                    repository = repository,
                    position = position
                )
            }
        }
    }
}

object RepositoryItemTestTags {
    const val NAME = "rep-name-"
    const val AUTHOR = "author-"
    const val AUTHOR_IMAGE = "author-avatar-"
    const val STARS = "stars-"
    const val FORKS = "forks-"
}

@Preview
@Composable
fun DefaultPreview() {
    RepositoryCard(
        RepositoryUiModel(
            1,
            "android",
            1000,
            2000,
            "Zeca",
            "url_image"
        ),
        position = 0
    )
}
