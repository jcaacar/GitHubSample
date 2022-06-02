package com.zeca.githubsample.features.repositories.ui.model

data class RepositoryUiModel(
    val id: Int,
    val name: String,
    val stars: Int,
    val forks: Int,
    val author: String,
    val authorAvatar: String
)
