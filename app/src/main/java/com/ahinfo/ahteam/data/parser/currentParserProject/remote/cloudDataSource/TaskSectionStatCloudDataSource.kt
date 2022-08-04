package com.ahinfo.ahteam.data.parser.currentParserProject.remote.cloudDataSource

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.api.GetTaskSectionStatApi
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.RequestGetSectionStat
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.mapper.SectionStatDataToDomainMapper
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetSectionStatDomain
import javax.inject.Inject

class TaskSectionStatCloudDataSource @Inject constructor(
    private val mapper: SectionStatDataToDomainMapper,
    private val api: GetTaskSectionStatApi,
    private val responseWrapper: ResponseWrapper
) {

    suspend fun getTaskSectionStat(
        taskId: Int
    ): BaseResult<GetSectionStatDomain, Failure> = responseWrapper.handleResponse(mapper) {
        api.getSectionStat(RequestGetSectionStat(taskId))
    }
}