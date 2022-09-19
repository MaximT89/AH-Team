package com.ahinfo.ahteam.data.parser.catalogElementsLinks.module

import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.api.ElementsLinksApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ElementsLinksApiModule {

    @Provides
    fun provideElementsLinksApi(retrofit: Retrofit): ElementsLinksApi =
        retrofit.create(ElementsLinksApi::class.java)
}