package com.zeca.githubsample.di

import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import com.zeca.githubsample.network.retrofit.di.RetrofitModule
import com.zeca.githubsample.remote.repositories.api.contracts.RepositoriesAPI

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RetrofitModule::class]
)
object RetrofitTestModule {

    @Singleton
    @Provides
    fun provideRepositoriesAPI(): RepositoriesAPI {
        return RetrofitRepositoriesAPIStub()
    }
}
