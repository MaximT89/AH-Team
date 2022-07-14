package com.ahinfo.ahteam.data.project.project_authorization.remote

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.project.project_authorization.remote.dto.ResponseTodo
import com.ahinfo.ahteam.domain.project.authorization.entity.AuthModelDomain
import javax.inject.Inject

class AuthDataToDomainMapper @Inject constructor() : Mapper<ResponseTodo, AuthModelDomain> {
    override fun map(data: ResponseTodo): AuthModelDomain {
        return AuthModelDomain(data.task)
    }
}