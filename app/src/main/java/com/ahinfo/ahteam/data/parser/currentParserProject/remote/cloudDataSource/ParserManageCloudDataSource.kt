package com.ahinfo.ahteam.data.parser.currentParserProject.remote.cloudDataSource

import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.api.ParserStopApi
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.RequestStopParsing
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.mapper.ParserStopDataToDomainMapper
import javax.inject.Inject

class ParserManageCloudDataSource @Inject constructor(
    private val api : ParserStopApi,
    private val mapper : ParserStopDataToDomainMapper,
    private val responseWrapper: ResponseWrapper
) {

    suspend fun parserStop(taskId : Int) = responseWrapper.handleResponse(mapper){
        api.getParserTaskStatus(RequestStopParsing(taskId))
    }
}