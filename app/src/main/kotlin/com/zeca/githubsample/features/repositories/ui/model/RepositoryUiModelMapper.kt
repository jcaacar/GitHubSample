package com.zeca.githubsample.features.repositories.ui.model

import javax.inject.Inject
import com.zeca.githubsample.common.Mapper
import com.zeca.githubsample.data.repositories.models.Repository

class RepositoryUiModelMapper @Inject constructor() : Mapper<Repository, RepositoryUiModel> {
    override fun map(from: Repository): RepositoryUiModel {
        return from.run {
            RepositoryUiModel(
                id,
                name,
                stars,
                forks,
                owner,
                ownerAvatar
            )
        }
    }
}
