package com.ahinfo.ahteam.data.parser.updateProject.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UpgradeProjectDataModule {

    abstract fun bindUpgradeProjectRepository()
}