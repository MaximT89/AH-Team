package com.ahinfo.ahteam.data.parser.updateProject.module

import com.ahinfo.ahteam.data.parser.updateProject.UpgradeProjectRepositoryImpl
import com.ahinfo.ahteam.domain.parser.updateProject.repository.UpgradeProjectRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UpgradeProjectDataModule {

    @Binds
    abstract fun bindUpgradeProjectRepository(upgradeProjectRepositoryImpl: UpgradeProjectRepositoryImpl): UpgradeProjectRepository
}