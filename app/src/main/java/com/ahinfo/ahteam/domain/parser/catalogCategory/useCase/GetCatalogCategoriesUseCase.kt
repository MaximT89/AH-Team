package com.ahinfo.ahteam.domain.parser.catalogCategory.useCase

import com.ahinfo.ahteam.data.parser.catalogCategory.remote.dto.RequestGetCatalogCategories
import com.ahinfo.ahteam.domain.parser.catalogCategory.repository.GetCatalogCategoriesRepository
import javax.inject.Inject

class GetCatalogCategoriesUseCase @Inject constructor(
    private val repository: GetCatalogCategoriesRepository
) {

    suspend fun getCatalogCategories(request: RequestGetCatalogCategories) =
        repository.getCatalogCategory(request)
}