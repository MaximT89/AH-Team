package com.ahinfo.ahteam.data.parser.addProject.module

import com.ahinfo.ahteam.data.parser.addProject.remote.AppProjectRepositoryImpl
import com.ahinfo.ahteam.domain.parser.addProject.repositoty.AddProjectRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AddProjectDataModule {

    @Binds
    abstract fun bindAddProjectRepository(addProjectRepositoryImpl: AppProjectRepositoryImpl): AddProjectRepository
}