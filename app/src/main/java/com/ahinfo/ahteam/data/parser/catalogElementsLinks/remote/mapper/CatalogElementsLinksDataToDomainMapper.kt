package com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.ResponseCatalogElementsLinks
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.CatalogElementDomain
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.CatalogElementsLinksDomain
import javax.inject.Inject

class CatalogElementsLinksDataToDomainMapper @Inject constructor() : Mapper<ResponseCatalogElementsLinks, CatalogElementsLinksDomain>{
    override fun map(data: ResponseCatalogElementsLinks): CatalogElementsLinksDomain {

        val list : MutableList<CatalogElementDomain>? = null
        data.elements?.forEach {
            list?.add(CatalogElementDomain(
                it?.article,
                it?.elementId,
                it?.img,
                it?.name,
                it?.oldPrice,
                it?.price
            ))
        }
        return CatalogElementsLinksDomain(list!!)
    }
}