package com.ahinfo.ahteam.data.parser.catalogCategory.remote.cloudDataSource

import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.catalogCategory.remote.api.GetCatalogCategoriesApi
import com.ahinfo.ahteam.data.parser.catalogCategory.remote.dto.RequestGetCatalogCategories
import com.ahinfo.ahteam.data.parser.catalogCategory.remote.mapper.CatalogCategoriesDataToDomainMapper
import javax.inject.Inject

class CatalogCategoriesCloudDataSource @Inject constructor(
    private val api: GetCatalogCategoriesApi,
    private val mapper: CatalogCategoriesDataToDomainMapper,
    private val responseWrapper: ResponseWrapper
) {

    suspend fun getCatalogCategories(request: RequestGetCatalogCategories) =
        responseWrapper.handleResponse(mapper) {
            api.getCatalogCategories(request)
        }
}