package com.ahinfo.ahteam.data.parser.updateProject.remote.api

import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.RequestUpdateProject
import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.ResponseUpdateProject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UpdateProjectApi {

    @POST("/parsing/update.project/")
    suspend fun updateProject(@Body requestUpdateProject: RequestUpdateProject): Response<ResponseUpdateProject>
}