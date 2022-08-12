package com.ahinfo.ahteam.data.parser.currentParserProject.remote.api

import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.RequestGetSectionStat
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.ResponseGetElementStat
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GetTaskElementStatApi {

    @POST("/parsing/parsing/get.elementStat/")
    suspend fun getElementStat(@Body request : RequestGetSectionStat) : Response<ResponseGetElementStat>
}