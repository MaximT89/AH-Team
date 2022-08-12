package com.ahinfo.ahteam.data.parser.updateTaskProject.module

import com.ahinfo.ahteam.data.parser.updateTaskProject.UpdateProjectTaskRepositoryImpl
import com.ahinfo.ahteam.domain.parser.updateTaskProject.repository.UpdateProjectTaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UpdateProjectTaskDataModule {

    @Binds
    abstract fun bindUpdateProjectTaskRepository(repository: UpdateProjectTaskRepositoryImpl): UpdateProjectTaskRepository
}