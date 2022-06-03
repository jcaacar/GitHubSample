package com.zeca.githubsample.design.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun RoundedLargeImage(url: String, requestSize: Int = 220, testTag: String = "") {
    Surface(
        modifier = Modifier.size(100.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colors
            .surface.copy(alpha = 0.2f)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .size(requestSize)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .testTag(testTag)
        )
    }
}
