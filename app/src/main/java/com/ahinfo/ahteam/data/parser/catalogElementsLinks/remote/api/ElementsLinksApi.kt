package com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.api

import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestGetElementsLinks
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestGetElementsLinksFilter
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestGetElementsLinksStat
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.response.ResponseGetElementsLinks
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.response.ResponseGetElementsLinksFilter
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.response.ResponseGetElementsLinksStat
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ElementsLinksApi {

    @POST("/parsing/parsing/get.elementslinks/")
    suspend fun getElementsLinks(@Body request: RequestGetElementsLinks): Response<ResponseGetElementsLinks>

    @POST("/parsing/get.elementslinks.filter/")
    suspend fun getElementsLinksFilter(@Body request: RequestGetElementsLinksFilter): Response<ResponseGetElementsLinksFilter>

    @POST("/parsing/get.elementslinks.stat/")
    suspend fun getElementsLinksStat(@Body request: RequestGetElementsLinksStat): Response<ResponseGetElementsLinksStat>
}