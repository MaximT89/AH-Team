package com.ahinfo.ahteam.data.parser.catalogElementsLinks.module

import com.ahinfo.ahteam.data.parser.catalogElementsLinks.ElementsLinksRepositoryImpl
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.repository.ElementsLinksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ElementsLinksDataModule {

    @Binds
    abstract fun bindElementsLinksRepository(repository: ElementsLinksRepositoryImpl):
            ElementsLinksRepository
}