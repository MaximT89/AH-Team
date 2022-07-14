package com.ahinfo.ahteam.data.parser.test.module

import com.ahinfo.ahteam.data.parser.test.remote.api.TestService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object TestApiModule {

    @Provides
    fun provideTestApi(retrofit: Retrofit) : TestService = retrofit.create(TestService::class.java)
}