package com.ahinfo.ahteam.data.parser.currentParserProject.remote.cloudDataSource

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.api.GetProjectTaskStatusApi
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.RequestGetParserTaskStatus
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.mapper.GetParserTaskStatusDataToDomainMapper
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetParserTaskStatusDomain
import javax.inject.Inject

class GetProjectTaskStatusCloudDataSource @Inject constructor(
    private val mapper: GetParserTaskStatusDataToDomainMapper,
    private val api: GetProjectTaskStatusApi,
    private val responseWrapper: ResponseWrapper
) {

    suspend fun getProjectTaskStatus(
        request: RequestGetParserTaskStatus
    ): BaseResult<GetParserTaskStatusDomain, Failure> = responseWrapper.handleResponse(mapper) {
        api.getParserTaskStatus(request)
    }
}