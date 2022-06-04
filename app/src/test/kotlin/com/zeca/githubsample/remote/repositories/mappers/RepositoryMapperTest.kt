package com.zeca.githubsample.remote.repositories.mappers

import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.zeca.githubsample.remote.repositories.api.responses.OwnerResponse
import com.zeca.githubsample.remote.repositories.api.responses.RepositoryResponse

class RepositoryMapperTest {

    @Test
    fun `should map with success`() {
        RepositoryResponse(
            id = 1,
            name = "name",
            stars = 100,
            forks = 50,
            owner = OwnerResponse(
                id = 1,
                login = "owner-login",
                avatarUrl = "owner-avatar-url"
            )
        ).run {
            RepositoryMapper().map(this).let {
                assertThat(it.id).isEqualTo(id)
                assertThat(it.name).isEqualTo(name)
                assertThat(it.stars).isEqualTo(stars)
                assertThat(it.forks).isEqualTo(forks)
                assertThat(it.owner).isEqualTo(owner.login)
                assertThat(it.ownerAvatar).isEqualTo(owner.avatarUrl)
            }
        }
    }
}
