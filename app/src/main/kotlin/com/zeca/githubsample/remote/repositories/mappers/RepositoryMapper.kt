package com.zeca.githubsample.remote.repositories.mappers

import com.zeca.githubsample.common.Mapper
import com.zeca.githubsample.data.repositories.models.Repository
import com.zeca.githubsample.remote.repositories.api.responses.RepositoryResponse

internal class RepositoryMapper : Mapper<RepositoryResponse, Repository> {
    override suspend fun map(from: RepositoryResponse): Repository {
        return from.run {
            Repository(
                id,
                name,
                starts,
                forks,
                owner.login,
                owner.avatarUrl
            )
        }
    }
}
