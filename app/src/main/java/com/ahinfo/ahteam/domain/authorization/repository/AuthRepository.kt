package com.ahinfo.ahteam.domain.authorization.repository

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.bases.Failure
import com.ahinfo.ahteam.domain.authorization.entity.AuthModelDomain

interface AuthRepository {

    suspend fun fetchAuthData() : BaseResult<AuthModelDomain, Failure>
}