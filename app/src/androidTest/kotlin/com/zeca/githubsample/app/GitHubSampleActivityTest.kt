package com.zeca.githubsample.app

import javax.inject.Inject
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.zeca.githubsample.design.components.Loading
import com.zeca.githubsample.di.RetrofitRepositoriesAPIStub
import com.zeca.githubsample.features.repositories.ui.model.RepositoryUiModel
import com.zeca.githubsample.features.repositories.ui.view.RepositoryItemTestTags
import com.zeca.githubsample.features.repositories.ui.view.RepositoryListTestTags
import com.zeca.githubsample.remote.repositories.api.contracts.RepositoriesAPI
import com.zeca.githubsample.remote.repositories.api.responses.SearchRepositoriesResponse

@HiltAndroidTest
class GitHubSampleActivityTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<GitHubSampleActivity>()

    @Inject
    lateinit var retroApi: RepositoriesAPI

    @Before
    fun setUp() {
        hiltTestRule.inject()
    }

    @Test
    fun shouldPresentLoading() {
        val api = retroApi as RetrofitRepositoriesAPIStub
        api.isLoading = true
        composeTestRule.onNodeWithTag(Loading.TAG)
            .assertIsDisplayed()
    }

    @Test
    fun shouldPresentGeneralError() {
        val api = retroApi as RetrofitRepositoriesAPIStub
        api.isError = true

        composeTestRule.onNodeWithText("Oops, something went wrong. Please try again later.")
    }

    @Test
    fun loadActivity_shouldLoadExpectedContent() {
        repositories().forEachIndexed(::verifyItem)
    }

    private fun verifyItem(position: Int, repo: RepositoryUiModel) {
        composeTestRule.onNodeWithTag(RepositoryListTestTags.LIST_TAG)
            .performScrollToIndex(position)

        composeTestRule.onNodeWithTag(RepositoryItemTestTags.NAME.plus(position))
            .assertTextEquals(repo.name).assertIsDisplayed()
        composeTestRule.onNodeWithTag(RepositoryItemTestTags.AUTHOR.plus(position))
            .assertTextEquals(repo.author).assertIsDisplayed()
        composeTestRule.onNodeWithTag(RepositoryItemTestTags.STARS.plus(position))
            .assertTextEquals(repo.stars.toString()).assertIsDisplayed()
        composeTestRule.onNodeWithTag(RepositoryItemTestTags.FORKS.plus(position))
            .assertTextEquals(repo.forks.toString()).assertIsDisplayed()

        composeTestRule.onNodeWithTag(RepositoryItemTestTags.AUTHOR_IMAGE.plus(position))
            .assertExists()
    }

    private fun repositories(): List<RepositoryUiModel> {
        val json =
            RetrofitRepositoriesAPIStub::class.java.classLoader!!.getResource("github_http_ok.json")!!
                .readText()
        val typeToken = object : TypeToken<SearchRepositoriesResponse>() {}.type
        val response = Gson().fromJson<SearchRepositoriesResponse>(json, typeToken)

        return response.repositories.map {
            RepositoryUiModel(
                id = it.id,
                name = it.name,
                author = it.owner.login,
                stars = it.stars,
                forks = it.forks,
                authorAvatar = ""
            )
        }
    }
}
