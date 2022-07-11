package com.ahinfo.ahteam.data.authorization.remote

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.bases.Failure
import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.authorization.remote.api.AuthService
import com.ahinfo.ahteam.domain.authorization.entity.AuthModelDomain
import javax.inject.Inject

class AuthCloudDataSource @Inject constructor(
    private val api : AuthService,
    private val responseWrapper: ResponseWrapper.Base,
    private val mapper : AuthDataToDomainMapper
){

    suspend fun fetchAuthData() : BaseResult<AuthModelDomain, Failure> =
        responseWrapper.handleResponse(mapper){
            api.signIn()
        }

}