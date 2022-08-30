package com.ahinfo.ahteam.data.parser.catalogCategory.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.catalogCategory.remote.dto.ResponseGetCatalogCategories
import com.ahinfo.ahteam.domain.parser.catalogCategory.entity.GetCatalogCategoriesDomain
import javax.inject.Inject

class CatalogCategoriesDataToDomainMapper @Inject constructor() :
    Mapper<ResponseGetCatalogCategories, GetCatalogCategoriesDomain> {
    override fun map(data: ResponseGetCatalogCategories): GetCatalogCategoriesDomain {
        return GetCatalogCategoriesDomain(data.elements)
    }
}