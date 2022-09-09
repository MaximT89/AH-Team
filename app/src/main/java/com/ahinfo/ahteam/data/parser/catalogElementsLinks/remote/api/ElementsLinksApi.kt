package com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.api

import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestElementsLinks
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestElementsLinksFilter
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestElementsLinksStat
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.response.ResponseElementsLinks
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.response.ResponseElementsLinksFilter
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.response.ResponseElementsLinksStat
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ElementsLinksApi {

    @POST("/parsing/parsing/get.elementslinks/")
    suspend fun getElementsLinks(@Body request: RequestElementsLinks): Response<ResponseElementsLinks>

    @POST("/parsing/get.elementslinks.filter/")
    suspend fun getElementsLinksFilter(@Body request: RequestElementsLinksFilter): Response<ResponseElementsLinksFilter>

    @POST("/parsing/get.elementslinks.stat/")
    suspend fun getElementsLinksStat(@Body request: RequestElementsLinksStat): Response<ResponseElementsLinksStat>
}