package com.ahinfo.ahteam.data.parser.addProject.remote

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.addProject.remote.api.AddProjectApi
import com.ahinfo.ahteam.data.parser.addProject.remote.dto.RequestAddProject
import com.ahinfo.ahteam.data.parser.addProject.remote.mapper.AddProjectDataToDomainMapper
import com.ahinfo.ahteam.domain.parser.addProject.entity.AddProjectDomain
import javax.inject.Inject

class AddProjectCloudDataSource @Inject constructor(
    private val api: AddProjectApi,
    private val mapper: AddProjectDataToDomainMapper,
    private val responseWrapper: ResponseWrapper
) {

    suspend fun addProject(requestAddProject: RequestAddProject): BaseResult<AddProjectDomain, Failure> =
        responseWrapper.handleResponse(mapper) {
            api.addProject(requestAddProject)
        }
}