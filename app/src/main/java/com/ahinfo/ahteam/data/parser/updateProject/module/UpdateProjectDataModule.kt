package com.ahinfo.ahteam.data.parser.updateProject.module

import com.ahinfo.ahteam.data.parser.updateProject.UpdateProjectRepositoryImpl
import com.ahinfo.ahteam.domain.parser.updateProject.repository.UpdateProjectRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UpdateProjectDataModule {

    @Binds
    abstract fun bindUpdateProjectRepository(updateProjectRepositoryImpl: UpdateProjectRepositoryImpl): UpdateProjectRepository
}