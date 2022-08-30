package com.ahinfo.ahteam.domain.parser.catalogCategory.entity

import com.ahinfo.ahteam.data.parser.catalogCategory.remote.dto.ElementsItem

data class GetCatalogCategoriesDomain(
    val listElements : List<ElementsItem?>? = null
)
