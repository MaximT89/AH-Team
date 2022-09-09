package com.ahinfo.ahteam.data.parser.catalogElementsLinks.module

import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.api.ElementsLinksApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit

@Module
@InstallIn
object ElementsLinksApiModule {

    @Provides
    fun provideElementsLinksApi(retrofit: Retrofit): ElementsLinksApi =
        retrofit.create(ElementsLinksApi::class.java)
}