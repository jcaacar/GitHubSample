package com.zeca.githubsample.design.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeca.githubsample.R

@Composable
fun IconText(
    @DrawableRes resId: Int,
    text: String,
    modifier: Modifier = Modifier,
    textTag: String = ""
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(id = resId),
            tint = Color.Gray,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .padding(end = 6.dp)
        )
        Text(
            text = text,
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            modifier = Modifier.testTag(textTag)
        )
    }
}

@Preview
@Composable
fun IconTextPreview() {
    IconText(
        resId = R.drawable.ic_baseline_star_border_24,
        text = "999"
    )
}
