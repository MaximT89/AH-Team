package com.ahinfo.ahteam.data.parser.currentParserProject.remote.api

import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.RequestGetSectionStat
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.ResponseGetSectionsStat
import retrofit2.Response
import retrofit2.http.*

interface GetTaskSectionStatApi {

    @POST("/parsing/parsing/get.sectionsStat/")
    suspend fun getSectionStat(@Body request : RequestGetSectionStat) : Response<ResponseGetSectionsStat>
}