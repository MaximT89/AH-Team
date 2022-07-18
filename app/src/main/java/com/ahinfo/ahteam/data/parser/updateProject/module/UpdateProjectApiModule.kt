package com.ahinfo.ahteam.data.parser.updateProject.module

import com.ahinfo.ahteam.data.parser.updateProject.remote.api.UpdateProjectApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object UpdateProjectApiModule {

    @Provides
    fun provideUpdateProjectApi(retrofit: Retrofit): UpdateProjectApi =
        retrofit.create(UpdateProjectApi::class.java)
}