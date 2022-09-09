package com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request

import com.google.gson.annotations.SerializedName

data class RequestGetElementsLinksFilter(

	@field:SerializedName("filter")
	val filterLinks: Filter? = null,

	@field:SerializedName("search")
	val search: String? = null,

	@field:SerializedName("parsing_id")
	val parsingId: Int? = null
)

data class Filter(

	@field:SerializedName("exist")
	val exist: Boolean? = null,

	@field:SerializedName("section_id")
	val sectionId: List<Int?>? = null,

	@field:SerializedName("price")
	val price: List<Int?>? = null
)
