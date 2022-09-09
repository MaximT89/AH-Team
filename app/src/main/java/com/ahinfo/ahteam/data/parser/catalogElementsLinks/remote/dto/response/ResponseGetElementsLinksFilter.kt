package com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.response

import com.google.gson.annotations.SerializedName

data class ResponseGetElementsLinksFilter(

	@field:SerializedName("filters")
	val filters: Filters? = null
)

data class Exist(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("value")
	val value: List<Int?>? = null
)

data class SectionId(

	@field:SerializedName("values")
	val values: List<ValuesItem?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class Filters(

	@field:SerializedName("exist")
	val exist: Exist? = null,

	@field:SerializedName("section_id")
	val sectionId: SectionId? = null,

	@field:SerializedName("price")
	val price: Price? = null
)

data class Range(

	@field:SerializedName("min")
	val min: Int? = null,

	@field:SerializedName("max")
	val max: Int? = null
)

data class ValuesItem(

	@field:SerializedName("section_id")
	val sectionId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class Price(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("range")
	val range: Range? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)
