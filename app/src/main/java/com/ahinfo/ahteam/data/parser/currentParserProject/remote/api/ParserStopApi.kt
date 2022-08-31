package com.ahinfo.ahteam.data.parser.currentParserProject.remote.api

import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.RequestStopParsing
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.ResponseParserStop
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ParserStopApi {

    @POST("/parsing/parsing/stop.parsingelement/")
    suspend fun getParserTaskStatus(@Body request: RequestStopParsing): Response<ResponseParserStop>
}