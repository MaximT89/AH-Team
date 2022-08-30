package com.ahinfo.ahteam.data.parser.catalogCategory.module

import com.ahinfo.ahteam.data.parser.catalogCategory.GetCatalogCategoriesRepositoryImpl
import com.ahinfo.ahteam.domain.parser.catalogCategory.repository.GetCatalogCategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CatalogCategoriesDataModule {

    @Binds
    abstract fun bindGetCatalogCategoriesRepository(repository: GetCatalogCategoriesRepositoryImpl) :
            GetCatalogCategoriesRepository
}