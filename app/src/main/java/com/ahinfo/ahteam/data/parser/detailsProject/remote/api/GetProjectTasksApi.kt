package com.ahinfo.ahteam.data.parser.detailsProject.remote.api

import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.RequestGetProjectTasks
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ResponseGetProjectTasks
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GetProjectTasksApi {

    /**
     * Данным запросом получаем все задачи по парсингу для проекта
     */
    @POST("/parsing/parsing/get.parsings/")
    suspend fun getProjectTasks(@Body request: RequestGetProjectTasks): Response<ResponseGetProjectTasks>
}