package com.ahinfo.ahteam.data.project.project_authorization.remote

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.project.project_authorization.remote.api.AuthService
import com.ahinfo.ahteam.domain.project.authorization.entity.AuthModelDomain
import javax.inject.Inject

class AuthDataCloudSource @Inject constructor(
    private val api : AuthService,
    private val responseWrapper: ResponseWrapper,
    private val mapper : AuthDataToDomainMapper
){

    suspend fun fetchAuthData() : BaseResult<AuthModelDomain, Failure> =
        responseWrapper.handleResponse(mapper){
            api.signIn()
        }

}