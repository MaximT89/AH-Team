package com.ahinfo.ahteam.data.parser.test.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseTestData(

	@field:SerializedName("items_left")
	val itemsLeft: Int? = null,

	@field:SerializedName("total_items")
	val totalItems: Int? = null
)
