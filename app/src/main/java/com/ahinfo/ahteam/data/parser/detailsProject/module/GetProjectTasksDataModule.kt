package com.ahinfo.ahteam.data.parser.detailsProject.module

import com.ahinfo.ahteam.data.parser.detailsProject.GetProjectTasksRepositoryImpl
import com.ahinfo.ahteam.domain.parser.detailsProject.repositoty.GetProjectTasksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GetProjectTasksDataModule {

    @Binds
    abstract fun bindGetProjectTasksRepository(repository: GetProjectTasksRepositoryImpl): GetProjectTasksRepository
}