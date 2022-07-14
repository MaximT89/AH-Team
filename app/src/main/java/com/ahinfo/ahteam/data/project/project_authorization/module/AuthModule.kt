package com.ahinfo.ahteam.data.project.project_authorization.module

import com.ahinfo.ahteam.data.project.project_authorization.AuthRepositoryImpl
import com.ahinfo.ahteam.domain.project.authorization.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl) : AuthRepository
}