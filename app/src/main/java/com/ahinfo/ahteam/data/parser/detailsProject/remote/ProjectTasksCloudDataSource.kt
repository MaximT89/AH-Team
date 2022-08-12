package com.ahinfo.ahteam.data.parser.detailsProject.remote

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.detailsProject.remote.api.DeleteProjectTaskApi
import com.ahinfo.ahteam.data.parser.detailsProject.remote.api.GetProjectTasksApi
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.RequestDeleteProjectTask
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.RequestGetProjectTasks
import com.ahinfo.ahteam.data.parser.detailsProject.remote.mapper.DeleteProjectTaskDataToDomainMapper
import com.ahinfo.ahteam.data.parser.detailsProject.remote.mapper.GetProjectTasksDataToDomainMapper
import com.ahinfo.ahteam.domain.parser.detailsProject.entity.DeleteProjectTaskDomain
import com.ahinfo.ahteam.domain.parser.detailsProject.entity.GetProjectTasksDomain
import javax.inject.Inject

class ProjectTasksCloudDataSource @Inject constructor(
    private val mapperGet: GetProjectTasksDataToDomainMapper,
    private val mapperDelete: DeleteProjectTaskDataToDomainMapper,
    private val apiGet: GetProjectTasksApi,
    private val apiDelete: DeleteProjectTaskApi,
    private val responseWrapper: ResponseWrapper
) {

    suspend fun getProjectTasks(
        request: RequestGetProjectTasks
    ): BaseResult<GetProjectTasksDomain, Failure> = responseWrapper.handleResponse(mapperGet) {
        apiGet.getProjectTasks(request)
    }

    suspend fun deleteProjectTask(
        request: RequestDeleteProjectTask
    ): BaseResult<DeleteProjectTaskDomain, Failure> = responseWrapper.handleResponse(mapperDelete){
        apiDelete.deleteProjectTasks(request)
    }
}