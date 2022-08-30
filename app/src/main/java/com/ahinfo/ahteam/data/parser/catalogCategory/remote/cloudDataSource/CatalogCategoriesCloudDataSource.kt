package com.ahinfo.ahteam.data.parser.catalogCategory.remote.cloudDataSource

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.catalogCategory.remote.api.GetCatalogCategoriesApi
import com.ahinfo.ahteam.data.parser.catalogCategory.remote.dto.RequestGetCatalogCategories
import com.ahinfo.ahteam.data.parser.catalogCategory.remote.mapper.CatalogCategoriesDataToDomainMapper
import com.ahinfo.ahteam.domain.parser.catalogCategory.entity.GetCatalogCategoriesDomain
import javax.inject.Inject

class CatalogCategoriesCloudDataSource @Inject constructor(
    private val api: GetCatalogCategoriesApi,
    private val mapper: CatalogCategoriesDataToDomainMapper,
    private val responseWrapper: ResponseWrapper
) {

    suspend fun getCatalogCategories(request: RequestGetCatalogCategories): BaseResult<GetCatalogCategoriesDomain, Failure> =
        responseWrapper.handleResponse(mapper) {
            api.getCatalogCategories(request)
        }

}