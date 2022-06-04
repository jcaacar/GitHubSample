package com.zeca.githubsample.remote.repositories.mappers

import com.zeca.githubsample.common.Mapper
import com.zeca.githubsample.data.repositories.models.Repository
import com.zeca.githubsample.remote.repositories.api.responses.RepositoryResponse

class RepositoryMapper : Mapper<RepositoryResponse, Repository> {
    override fun map(from: RepositoryResponse): Repository {
        return from.run {
            Repository(
                id,
                name,
                stars,
                forks,
                owner.login,
                owner.avatarUrl
            )
        }
    }
}
