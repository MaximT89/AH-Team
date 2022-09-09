package com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity

data class ElementsLinksDomain(
    val elements: List<ElementLinks>
)

data class ElementLinks(
    val article: String? = null,
    val elementId: Int? = null,
    val img: String? = null,
    val name: String? = null,
    val price: Int? = null,
    val oldPrice: Int? = null
)