package com.ahinfo.ahteam.data.parser.detailsProject.remote.api

import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.RequestDeleteProjectTask
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ResponseDeleteProjectTask
import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ResponseDeleteProject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DeleteProjectTaskApi {

    @POST("/parsing/parsing/del.parsing/")
    suspend fun deleteProjectTasks(@Body request: RequestDeleteProjectTask): Response<ResponseDeleteProjectTask>
}