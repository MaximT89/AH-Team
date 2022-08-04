package com.ahinfo.ahteam.data.parser.currentParserProject.module

import com.ahinfo.ahteam.data.parser.currentParserProject.ParserTaskRepositoryImpl
import com.ahinfo.ahteam.domain.parser.currentParserProject.repository.ParserTaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProjectTaskDataModule {

    @Binds
    abstract fun bindGetParserTaskStatusRepository(repository: ParserTaskRepositoryImpl):
            ParserTaskRepository
}
