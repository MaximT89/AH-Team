package com.ahinfo.ahteam.data.parser.addProjectTask.module

import com.ahinfo.ahteam.data.parser.addProjectTask.remote.api.AddProjectTaskApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AddProjectTaskApiModule {

    @Provides
    fun providesAddProjectTaskApi(retrofit: Retrofit) : AddProjectTaskApi =
        retrofit.create(AddProjectTaskApi::class.java)
}