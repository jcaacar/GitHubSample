package com.zeca.githubsample.data.repositories.models

data class Repository(
    val id: Int,
    val name: String,
    val stars: Int,
    val forks: Int,
    val owner: String,
    val ownerAvatar: String
)
