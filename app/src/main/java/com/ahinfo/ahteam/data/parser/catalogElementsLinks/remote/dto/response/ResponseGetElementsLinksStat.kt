package com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.response

import com.google.gson.annotations.SerializedName

data class ResponseGetElementsLinksStat(

	@field:SerializedName("stat")
	val stat: Stat? = null
)

data class Stat(

	@field:SerializedName("count_elements")
	val countElements: Int? = null,

	@field:SerializedName("max_price")
	val maxPrice: Int? = null,

	@field:SerializedName("min_price")
	val minPrice: Int? = null,

	@field:SerializedName("avg_price")
	val avgPrice: Int? = null,

	@field:SerializedName("avg_weight")
	val avgWeight: Double? = null
)
