package com.ahinfo.ahteam.data.parser.currentParserProject.module

import com.ahinfo.ahteam.data.parser.currentParserProject.GetParserTaskStatusRepositoryImpl
import com.ahinfo.ahteam.domain.parser.currentParserProject.repository.GetParserTaskStatusRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GetProjectTaskStatusDataModule {

    @Binds
    abstract fun bindGetParserTaskStatusRepository(repository: GetParserTaskStatusRepositoryImpl):
            GetParserTaskStatusRepository
}
