package com.ahinfo.ahteam.data.parser.currentParserProject.module

import com.ahinfo.ahteam.data.parser.currentParserProject.remote.api.GetTaskSectionStatApi
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.api.GetTaskStatusApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ProjectTaskModule {

    @Provides
    fun provideGetProjectTaskStatusApi(retrofit: Retrofit) : GetTaskStatusApi =
        retrofit.create(GetTaskStatusApi::class.java)

    @Provides
    fun provideGetTaskSectionStatApi(retrofit: Retrofit) : GetTaskSectionStatApi =
        retrofit.create(GetTaskSectionStatApi::class.java)
}