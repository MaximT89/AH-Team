package com.ahinfo.ahteam.data.authorization.remote

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.authorization.remote.dto.ResponseTodo
import com.ahinfo.ahteam.domain.authorization.entity.AuthModelDomain
import javax.inject.Inject

class AuthDataToDomainMapper @Inject constructor() : Mapper<ResponseTodo, AuthModelDomain> {
    override fun map(data: ResponseTodo): AuthModelDomain {
        return AuthModelDomain(data.task)
    }
}