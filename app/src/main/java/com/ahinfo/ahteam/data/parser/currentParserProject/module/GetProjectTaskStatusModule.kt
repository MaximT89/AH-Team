package com.ahinfo.ahteam.data.parser.currentParserProject.module

import com.ahinfo.ahteam.data.parser.currentParserProject.remote.api.GetProjectTaskStatusApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object GetProjectTaskStatusModule {

    @Provides
    fun provideGetProjectTaskStatusApi(retrofit: Retrofit) : GetProjectTaskStatusApi =
        retrofit.create(GetProjectTaskStatusApi::class.java)
}