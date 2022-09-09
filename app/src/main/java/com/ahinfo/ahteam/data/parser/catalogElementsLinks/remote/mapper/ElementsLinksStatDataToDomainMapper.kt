package com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.response.ResponseElementsLinksStat
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.ElementsLinksStatDomain
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.StatDomain
import javax.inject.Inject

class ElementsLinksStatDataToDomainMapper @Inject constructor() :
    Mapper<ResponseElementsLinksStat, ElementsLinksStatDomain> {
    override fun map(data: ResponseElementsLinksStat) =
        ElementsLinksStatDomain(
            StatDomain(
                countElements = data.stat?.countElements,
                maxPrice = data.stat?.maxPrice,
                minPrice = data.stat?.minPrice,
                avgPrice = data.stat?.avgPrice,
                avgWeight = data.stat?.avgWeight
            )
        )
}