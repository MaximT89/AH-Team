package com.ahinfo.ahteam.data.parser.updateTaskProject.remote

import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.updateTaskProject.remote.api.UpdateProjectTaskApi
import com.ahinfo.ahteam.data.parser.updateTaskProject.remote.dto.RequestUpdateProjectTask
import com.ahinfo.ahteam.data.parser.updateTaskProject.remote.mapper.UpdateProjectTaskDataToDomainMapper
import javax.inject.Inject

class UpdateProjectTaskCloudDataSource @Inject constructor(
    private val api: UpdateProjectTaskApi,
    private val mapper: UpdateProjectTaskDataToDomainMapper,
    private val responseWrapper: ResponseWrapper
) {

    suspend fun updateProjectTask(request: RequestUpdateProjectTask) =
        responseWrapper.handleResponse(mapper) {
            api.updateProject(request)
        }
}