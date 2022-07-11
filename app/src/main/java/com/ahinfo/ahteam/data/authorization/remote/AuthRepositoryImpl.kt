package com.ahinfo.ahteam.data.authorization.remote

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.bases.Failure
import com.ahinfo.ahteam.domain.authorization.entity.AuthModelDomain
import com.ahinfo.ahteam.domain.authorization.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val cloudDataSource: AuthCloudDataSource
) : AuthRepository {

    override suspend fun fetchAuthData(): BaseResult<AuthModelDomain, Failure> {
        return cloudDataSource.fetchAuthData()
    }
}