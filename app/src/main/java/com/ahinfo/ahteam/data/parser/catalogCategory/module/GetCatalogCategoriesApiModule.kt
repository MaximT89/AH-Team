package com.ahinfo.ahteam.data.parser.catalogCategory.module

import com.ahinfo.ahteam.data.parser.catalogCategory.remote.api.GetCatalogCategoriesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object GetCatalogCategoriesApiModule {

    @Provides
    fun providesGetCatalogCategoryApi(retrofit: Retrofit): GetCatalogCategoriesApi =
        retrofit.create(GetCatalogCategoriesApi::class.java)
}