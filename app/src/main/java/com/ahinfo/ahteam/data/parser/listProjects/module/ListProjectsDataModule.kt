package com.ahinfo.ahteam.data.parser.listProjects.module

import com.ahinfo.ahteam.data.parser.listProjects.ListProjectsRepositoryImpl
import com.ahinfo.ahteam.domain.parser.listProjects.repositoty.ListProjectsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ListProjectsDataModule {

    @Binds
    abstract fun bindListProjectRepository(repository: ListProjectsRepositoryImpl): ListProjectsRepository
}