package com.ahinfo.ahteam.data.parser.updateProject.remote

import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.updateProject.remote.api.UpdateProjectApi
import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.RequestUpdateProject
import com.ahinfo.ahteam.data.parser.updateProject.remote.mapper.UpdateProjectDataToDomainMapper
import javax.inject.Inject

class UpdateProjectDataSource @Inject constructor(
    private val responseWrapper: ResponseWrapper,
    private val mapper: UpdateProjectDataToDomainMapper,
    private val api: UpdateProjectApi
) {

    suspend fun updateProject(requestUpdateProject: RequestUpdateProject) =
        responseWrapper.handleResponse(mapper) {
            api.updateProject(requestUpdateProject)
        }
}