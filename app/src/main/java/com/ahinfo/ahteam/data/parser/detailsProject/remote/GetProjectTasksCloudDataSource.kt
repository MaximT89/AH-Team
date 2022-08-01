package com.ahinfo.ahteam.data.parser.detailsProject.remote

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.detailsProject.remote.api.GetProjectTasksApi
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.RequestGetProjectTasks
import com.ahinfo.ahteam.data.parser.detailsProject.remote.mapper.GetProjectTasksDataToDomainMapper
import com.ahinfo.ahteam.domain.parser.detailsProject.entity.GetProjectTasksDomain
import javax.inject.Inject

class GetProjectTasksCloudDataSource @Inject constructor(
    private val mapper: GetProjectTasksDataToDomainMapper,
    private val api: GetProjectTasksApi,
    private val responseWrapper: ResponseWrapper
) {

    suspend fun getProjectTasks(
        request: RequestGetProjectTasks
    ): BaseResult<GetProjectTasksDomain, Failure> = responseWrapper.handleResponse(mapper) {
        api.getProjectTasks(
            request
        )
    }
}