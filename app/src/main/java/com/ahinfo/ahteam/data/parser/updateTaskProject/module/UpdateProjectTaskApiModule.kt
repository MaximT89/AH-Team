package com.ahinfo.ahteam.data.parser.updateTaskProject.module

import com.ahinfo.ahteam.data.parser.updateTaskProject.remote.api.UpdateProjectTaskApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object UpdateProjectTaskApiModule {

    @Provides
    fun provideUpdateProjectTaskApi(retrofit: Retrofit) : UpdateProjectTaskApi =
        retrofit.create(UpdateProjectTaskApi::class.java)
}