package com.zeca.githubsample.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import com.zeca.githubsample.design.theme.GithubSampleTheme
import com.zeca.githubsample.features.repositories.ui.view.RepositoryListScreen

@AndroidEntryPoint
class GitHubSampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubSampleTheme {
                RepositoryListScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GithubSampleTheme {
        RepositoryListScreen()
    }
}
