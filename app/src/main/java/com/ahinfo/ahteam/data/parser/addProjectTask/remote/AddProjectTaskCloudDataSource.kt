package com.ahinfo.ahteam.data.parser.addProjectTask.remote

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.addProjectTask.remote.api.AddProjectTaskApi
import com.ahinfo.ahteam.data.parser.addProjectTask.remote.dto.RequestAddProjectTask
import com.ahinfo.ahteam.data.parser.addProjectTask.remote.mapper.AddProjectTaskDataToDomainMapper
import com.ahinfo.ahteam.domain.parser.addProjectTask.entity.AddProjectTaskDomain
import javax.inject.Inject

class AddProjectTaskCloudDataSource @Inject constructor(
    private val api: AddProjectTaskApi,
    private val mapper: AddProjectTaskDataToDomainMapper,
    private val responseWrapper: ResponseWrapper
) {

    suspend fun addProject(request: RequestAddProjectTask): BaseResult<AddProjectTaskDomain, Failure> =
        responseWrapper.handleResponse(mapper) {
            api.addProject(request)
        }
}