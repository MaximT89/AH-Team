package com.ahinfo.ahteam.domain.project.authorization.repository

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.domain.project.authorization.entity.AuthModelDomain

interface AuthRepository {

    suspend fun fetchAuthData() : BaseResult<AuthModelDomain, Failure>
}