package com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity

import com.google.gson.annotations.SerializedName

data class ElementsLinksFilterDomain(
	val filters: FiltersDomain? = null
)

data class RangeDomain(
	val min: Int? = null,
	val max: Int? = null
)

data class SectionIdDomain(
	val values: List<ValuesItemDomain?>? = null,
	val name: String? = null,
	val title: String? = null,
	val type: String? = null
)

data class ValuesItemDomain(
	val sectionId: Int? = null,
	val name: String? = null
)

data class PriceDomain(
	val name: String? = null,
	val range: RangeDomain? = null,
	val title: String? = null,
	val type: String? = null
)

data class FiltersDomain(
	val exist: ExistDomain? = null,
	val sectionId: SectionIdDomain? = null,
	val price: PriceDomain? = null
)

data class ExistDomain(
	val name: String? = null,
	val title: String? = null,
	val type: String? = null,
	val value: List<Int?>? = null
)