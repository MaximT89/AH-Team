package com.ahinfo.ahteam.data.parser.test.remote.api

import com.ahinfo.ahteam.data.parser.test.remote.dto.ResponseTestData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TestApi {

    /**
     * Запрашиваем тестовые данные для [TestFragment]
     * @id [id] default value = 10
     */
    @GET("/getstatistics/{id}")
    suspend fun getTestDataFromServer(@Path("id") id : Int) : Response<ResponseTestData>
}