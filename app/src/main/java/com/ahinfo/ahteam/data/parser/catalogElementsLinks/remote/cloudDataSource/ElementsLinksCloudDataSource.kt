package com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.cloudDataSource

import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.api.ElementsLinksApi
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestElementsLinks
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestElementsLinksFilter
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestElementsLinksStat
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.mapper.ElementsLinksDataToDomainMapper
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.mapper.ElementsLinksFilterDataToDomainMapper
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.mapper.ElementsLinksStatDataToDomainMapper
import javax.inject.Inject

class ElementsLinksCloudDataSource @Inject constructor(
    private val api: ElementsLinksApi,
    private val responseWrapper: ResponseWrapper,
    private val mapperCatalog: ElementsLinksDataToDomainMapper,
    private val mapperFilter: ElementsLinksFilterDataToDomainMapper,
    private val mapperStat: ElementsLinksStatDataToDomainMapper
) {

    suspend fun getElementsLinks(request: RequestElementsLinks) =
        responseWrapper.handleResponse(mapperCatalog) {
            api.getElementsLinks(request)
        }

    suspend fun getElementsLinksFilter(request: RequestElementsLinksFilter) =
        responseWrapper.handleResponse(mapperFilter) {
            api.getElementsLinksFilter(request)
        }

    suspend fun getElementsLinksStat(request: RequestElementsLinksStat) =
        responseWrapper.handleResponse(mapperStat) {
            api.getElementsLinksStat(request)
        }
}