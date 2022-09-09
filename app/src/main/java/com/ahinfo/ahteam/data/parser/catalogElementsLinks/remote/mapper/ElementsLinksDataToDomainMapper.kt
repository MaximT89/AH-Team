package com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.response.ResponseElementsLinks
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.ElementLinks
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.ElementsLinksDomain
import javax.inject.Inject

class ElementsLinksDataToDomainMapper @Inject constructor() :
    Mapper<ResponseElementsLinks, ElementsLinksDomain> {
    override fun map(data: ResponseElementsLinks): ElementsLinksDomain =
        ElementsLinksDomain(mutableListOf<ElementLinks>().apply {
            data.elements?.forEach {
                add(
                    ElementLinks(
                        it?.article,
                        it?.elementId,
                        it?.img,
                        it?.name,
                        it?.price,
                        it?.oldPrice
                    )
                )
            }
        })
}