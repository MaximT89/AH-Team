package com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity

data class CatalogElementsLinksDomain(
    val list : List<CatalogElementDomain>
)

data class CatalogElementDomain(
    val article : String?,
    val elementId : Int?,
    val img : String?,
    val name : String?,
    val oldPrice : Int?,
    val price : Int?,
)
