package com.ahinfo.ahteam.data.parser.currentParserProject.remote.cloudDataSource

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.api.GetTaskElementStatApi
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.api.GetTaskSectionStatApi
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.RequestGetSectionStat
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.mapper.ElementStatDataToDomainMapper
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.mapper.SectionStatDataToDomainMapper
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetElementStatDomain
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetSectionStatDomain
import javax.inject.Inject

class StatCloudDataSource @Inject constructor(
    private val mapperSectionStat: SectionStatDataToDomainMapper,
    private val mapperElementStat: ElementStatDataToDomainMapper,
    private val apiSectionStat: GetTaskSectionStatApi,
    private val apiElementStat: GetTaskElementStatApi,
    private val responseWrapper: ResponseWrapper
) {

    suspend fun getTaskSectionStat(
        taskId: Int
    ): BaseResult<GetSectionStatDomain, Failure> =
        responseWrapper.handleResponse(mapperSectionStat) {
            apiSectionStat.getSectionStat(RequestGetSectionStat(taskId))
        }

    suspend fun getTaskElementStat(
        taskId: Int
    ): BaseResult<GetElementStatDomain, Failure> =
        responseWrapper.handleResponse(mapperElementStat) {
            apiElementStat.getElementStat(RequestGetSectionStat(taskId))
        }
}