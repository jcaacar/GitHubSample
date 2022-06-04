package com.zeca.githubsample.remote.repositories

import com.zeca.githubsample.remote.repositories.api.responses.OwnerResponse
import com.zeca.githubsample.remote.repositories.api.responses.RepositoryResponse

object RepositoryResponseHelper {

    fun build(count: Int): List<RepositoryResponse> {
        return MutableList(count) {
            build(
                id = it,
                name = "name-$it",
                stars = 100 + it,
                forks = 50 + it,
                ownerId = it,
                ownerName = "owner-$it",
                ownerAvatar = "avatarUrl-$it"
            )
        }
    }

    private fun build(
        id: Int = 1,
        name: String = "name",
        stars: Int = 100,
        forks: Int = 50,
        ownerId: Int = 1,
        ownerName: String = "owner",
        ownerAvatar: String = "avatarUrl"
    ): RepositoryResponse {
        return RepositoryResponse(
            id,
            name,
            stars,
            forks,
            OwnerResponse(
                ownerId,
                ownerName,
                ownerAvatar
            )
        )
    }
}
