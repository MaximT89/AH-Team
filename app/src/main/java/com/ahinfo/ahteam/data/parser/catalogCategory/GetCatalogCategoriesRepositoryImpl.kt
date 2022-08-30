package com.ahinfo.ahteam.data.parser.catalogCategory

import com.ahinfo.ahteam.data.parser.catalogCategory.remote.cloudDataSource.CatalogCategoriesCloudDataSource
import com.ahinfo.ahteam.data.parser.catalogCategory.remote.dto.RequestGetCatalogCategories
import com.ahinfo.ahteam.domain.parser.catalogCategory.repository.GetCatalogCategoriesRepository
import javax.inject.Inject

class GetCatalogCategoriesRepositoryImpl @Inject constructor(
    private val dataSource: CatalogCategoriesCloudDataSource
) : GetCatalogCategoriesRepository {

    override suspend fun getCatalogCategory(request: RequestGetCatalogCategories) =
        dataSource.getCatalogCategories(request)
}