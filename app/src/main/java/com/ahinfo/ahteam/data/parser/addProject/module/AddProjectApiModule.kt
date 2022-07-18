package com.ahinfo.ahteam.data.parser.addProject.module

import com.ahinfo.ahteam.data.parser.addProject.remote.api.AddProjectApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AddProjectApiModule {

    @Provides
    fun provideAddProjectApi(retrofit: Retrofit): AddProjectApi =
        retrofit.create(AddProjectApi::class.java)
}