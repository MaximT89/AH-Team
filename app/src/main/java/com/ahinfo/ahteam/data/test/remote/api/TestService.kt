package com.ahinfo.ahteam.data.test.remote.api

import com.ahinfo.ahteam.data.test.remote.dto.ResponseTestData
import retrofit2.Response
import retrofit2.http.GET

interface TestService {

    @GET("/getstatistics/10")
    suspend fun getTestDataFromServer() : Response<ResponseTestData>
}