package com.ahinfo.ahteam.data.parser.addProject.remote.api

import com.ahinfo.ahteam.data.parser.addProject.remote.dto.RequestAddProject
import com.ahinfo.ahteam.data.parser.addProject.remote.dto.ResponseAddProject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PUT

interface AddProjectApi {

    @FormUrlEncoded
    @PUT("/parsing/add.project/")
    suspend fun addProject(@Body requestAddProject: RequestAddProject): Response<ResponseAddProject>
}