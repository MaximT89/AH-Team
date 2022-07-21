package com.ahinfo.ahteam.data.parser.detailsProject.module

import com.ahinfo.ahteam.data.parser.detailsProject.remote.api.GetProjectTasksApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object GetProjectTasksModule {

    @Provides
    fun provideGetProjectTasksApi(retrofit: Retrofit): GetProjectTasksApi =
        retrofit.create(GetProjectTasksApi::class.java)
}