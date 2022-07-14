package com.ahinfo.ahteam.data.parser.listProjects.module

import com.ahinfo.ahteam.data.parser.listProjects.remote.api.ListProjectsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ListProjectsApiModule {

    @Provides
    fun provideListModuleApi(retrofit: Retrofit): ListProjectsApi =
        retrofit.create(ListProjectsApi::class.java)
}