package com.ahinfo.ahteam.data.parser.updateProject.module

import com.ahinfo.ahteam.data.parser.updateProject.remote.api.UpgradeProjectApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object UpdateProjectApiModule {

    @Provides
    fun provideUpgradeProjectApi(retrofit: Retrofit): UpgradeProjectApi =
        retrofit.create(UpgradeProjectApi::class.java)
}