package com.ahinfo.ahteam.data.parser.updateTaskProject.remote.api

import com.ahinfo.ahteam.data.parser.updateTaskProject.remote.dto.RequestUpdateProjectTask
import com.ahinfo.ahteam.data.parser.updateTaskProject.remote.dto.ResponseUpdateProjectTask
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UpdateProjectTaskApi {

    @POST("/parsing/parsing/update.parsing/")
    suspend fun updateProject(@Body request: RequestUpdateProjectTask):
            Response<ResponseUpdateProjectTask>
}