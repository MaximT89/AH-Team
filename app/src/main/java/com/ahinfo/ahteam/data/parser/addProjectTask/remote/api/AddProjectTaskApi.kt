package com.ahinfo.ahteam.data.parser.addProjectTask.remote.api

import com.ahinfo.ahteam.data.parser.addProjectTask.remote.dto.RequestAddProjectTask
import com.ahinfo.ahteam.data.parser.addProjectTask.remote.dto.ResponseAddProjectTask
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT

interface AddProjectTaskApi {

    @PUT("/parsing/parsing/add.parsing/")
    suspend fun addProject(@Body request: RequestAddProjectTask): Response<ResponseAddProjectTask>
}