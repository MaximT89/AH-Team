package com.ahinfo.ahteam.data.test.module

import com.ahinfo.ahteam.data.test.TestRepositoryImpl
import com.ahinfo.ahteam.domain.test.repository.TestRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TestModule {

    @Binds
    abstract fun bindTestRepository(repositoryImpl: TestRepositoryImpl): TestRepository
}