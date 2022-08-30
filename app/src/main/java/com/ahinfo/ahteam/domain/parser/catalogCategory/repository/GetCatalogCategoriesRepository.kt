package com.ahinfo.ahteam.domain.parser.catalogCategory.repository

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.catalogCategory.remote.dto.RequestGetCatalogCategories
import com.ahinfo.ahteam.domain.parser.catalogCategory.entity.GetCatalogCategoriesDomain

interface GetCatalogCategoriesRepository {

    suspend fun getCatalogCategory(request: RequestGetCatalogCategories): BaseResult<GetCatalogCategoriesDomain, Failure>
}