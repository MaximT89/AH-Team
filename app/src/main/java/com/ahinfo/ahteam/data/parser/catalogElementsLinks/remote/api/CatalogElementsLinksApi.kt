package com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.api

import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.RequestCatalogElementsLinks
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.ResponseCatalogElementsLinks
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CatalogElementsLinksApi {

    @POST("/parsing/parsing/get.elementslinks/")
    suspend fun getElementStat(@Body request : RequestCatalogElementsLinks) : Response<ResponseCatalogElementsLinks>
}