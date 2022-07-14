package com.ahinfo.ahteam.data.project.project_authorization.module

import com.ahinfo.ahteam.data.project.project_authorization.remote.api.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AuthApiModule {

    @Provides
    fun provideAuthService(retrofit: Retrofit) : AuthApi = retrofit.create(AuthApi::class.java)
}