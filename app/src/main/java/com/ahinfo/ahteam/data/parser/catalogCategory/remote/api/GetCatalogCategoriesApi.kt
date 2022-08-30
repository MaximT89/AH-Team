package com.ahinfo.ahteam.data.parser.catalogCategory.remote.api

import com.ahinfo.ahteam.data.parser.catalogCategory.remote.dto.RequestGetCatalogCategories
import com.ahinfo.ahteam.data.parser.catalogCategory.remote.dto.ResponseGetCatalogCategories
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GetCatalogCategoriesApi {

    //sdfhaskdf

    @POST("/parsing/parsing/get.sections/")
    suspend fun getCatalogCategories(@Body request : RequestGetCatalogCategories) : Response<ResponseGetCatalogCategories>
}