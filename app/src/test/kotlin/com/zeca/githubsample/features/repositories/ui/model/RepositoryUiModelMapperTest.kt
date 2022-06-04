package com.zeca.githubsample.features.repositories.ui.model

import com.google.common.truth.Truth
import org.junit.Assert.*

import org.junit.Test
import com.zeca.githubsample.data.repositories.models.Repository
import com.zeca.githubsample.remote.repositories.api.responses.OwnerResponse
import com.zeca.githubsample.remote.repositories.api.responses.RepositoryResponse
import com.zeca.githubsample.remote.repositories.mappers.RepositoryMapper

class RepositoryUiModelMapperTest {

    @Test
    fun `should map with success`() {
        Repository(
            id = 1,
            name = "name",
            stars = 100,
            forks = 50,
            owner = "owner",
            ownerAvatar = "ownerAvatar"
        ).run {
            RepositoryUiModelMapper().map(this).let {
                Truth.assertThat(it.id).isEqualTo(id)
                Truth.assertThat(it.name).isEqualTo(name)
                Truth.assertThat(it.stars).isEqualTo(stars)
                Truth.assertThat(it.forks).isEqualTo(forks)
                Truth.assertThat(it.author).isEqualTo(owner)
                Truth.assertThat(it.authorAvatar).isEqualTo(ownerAvatar)
            }
        }
    }
}
