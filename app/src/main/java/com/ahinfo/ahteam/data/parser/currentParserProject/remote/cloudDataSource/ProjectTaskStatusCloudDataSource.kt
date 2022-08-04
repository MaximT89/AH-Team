package com.ahinfo.ahteam.data.parser.currentParserProject.remote.cloudDataSource

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.api.GetTaskStatusApi
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.RequestGetParserTaskStatus
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.mapper.GetParserTaskStatusDataToDomainMapper
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetParserTaskStatusDomain
import javax.inject.Inject

class ProjectTaskStatusCloudDataSource @Inject constructor(
    private val mapperTaskStatus: GetParserTaskStatusDataToDomainMapper,
    private val apiTaskStatus: GetTaskStatusApi,
    private val responseWrapper: ResponseWrapper
) {

    suspend fun getProjectTaskStatus(
        request: RequestGetParserTaskStatus
    ): BaseResult<GetParserTaskStatusDomain, Failure> = responseWrapper.handleResponse(mapperTaskStatus) {
        apiTaskStatus.getParserTaskStatus(request)
    }
}