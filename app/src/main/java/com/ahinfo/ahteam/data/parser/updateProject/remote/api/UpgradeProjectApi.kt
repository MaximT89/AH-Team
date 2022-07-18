package com.ahinfo.ahteam.data.parser.updateProject.remote.api

import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.RequestUpgradeProject
import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.ResponseUpdateProject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UpgradeProjectApi {

    @POST("/parsing/update.project/")
    suspend fun upgradeProject(@Body requestUpgradeProject: RequestUpgradeProject): Response<ResponseUpdateProject>
}