package com.ahinfo.ahteam.data.parser.addProjectTask.module

import com.ahinfo.ahteam.data.parser.addProjectTask.AppProjectTaskRepositoryImpl
import com.ahinfo.ahteam.domain.parser.addProjectTask.repositoty.AddProjectTaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppProjectTaskDataModule {

    @Binds
    abstract fun bindAddProjectTaskRepository(repository: AppProjectTaskRepositoryImpl)
            : AddProjectTaskRepository
}