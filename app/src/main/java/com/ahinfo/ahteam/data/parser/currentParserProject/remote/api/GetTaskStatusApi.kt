package com.ahinfo.ahteam.data.parser.currentParserProject.remote.api

import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.RequestGetTaskStatus
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.ResponseGetParserTaskStatus
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GetTaskStatusApi {

    /**
     * Запрашиваем статус задачи в проекте
     */
    @POST("/parsing/parsing/get.parsings/")
    suspend fun getParserTaskStatus(@Body request: RequestGetTaskStatus): Response<ResponseGetParserTaskStatus>
}