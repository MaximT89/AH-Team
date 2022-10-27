package com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request

import com.google.gson.annotations.SerializedName

data class RequestElementsLinks(

    @field:SerializedName("filter")
    val filterLinks: FilterLinks? = null,

    @field:SerializedName("search")
    val search: String? = null,

    @field:SerializedName("parsing_id")
    val parsingId: Int? = null,

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("sort")
    val sort: Sort? = null,

    @field:SerializedName("count_items")
    val countItems: Int? = null
)

data class FilterLinks(

	@field:SerializedName("exist")
	val exist: Boolean? = null,

	@field:SerializedName("section_id")
	val sectionId: List<Int?>? = null,

	@field:SerializedName("price")
	val price: List<Int?>? = null
)

data class Sort(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("order")
	val order: String? = null
)
