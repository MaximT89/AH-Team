package com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseGetParserTaskStatus(

	@field:SerializedName("summary")
	val summary: Int? = null,

	@field:SerializedName("elements")
	val elements: List<ElementsItem?>? = null,

	@field:SerializedName("page_count")
	val pageCount: Int? = null
)

data class ElementsItem(

	@field:SerializedName("site")
	val site: Any? = null,

	@field:SerializedName("project_id")
	val projectId: Int? = null,

	@field:SerializedName("parsing_id")
	val parsingId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("unix_time")
	val unixTime: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
