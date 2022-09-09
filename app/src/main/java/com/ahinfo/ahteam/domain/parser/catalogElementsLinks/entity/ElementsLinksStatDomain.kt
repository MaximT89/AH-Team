package com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity

data class ElementsLinksStatDomain(
	val stat: StatDomain? = null
)

data class StatDomain(
	val countElements: Int? = null,
	val maxPrice: Int? = null,
	val minPrice: Int? = null,
	val avgPrice: Int? = null,
	val avgWeight: Double? = null
)

