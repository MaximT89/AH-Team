package com.ahinfo.ahteam.data.parser.listProjects.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseListProjects(

	@field:SerializedName("summary")
	val summary: Int? = null,

	@field:SerializedName("elements")
	val elements: List<ElementsItem?>? = null,

	@field:SerializedName("page_count")
	val pageCount: Int? = null
)

data class ElementsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("unix_time")
	val unixTime: Long? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("project_id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)
