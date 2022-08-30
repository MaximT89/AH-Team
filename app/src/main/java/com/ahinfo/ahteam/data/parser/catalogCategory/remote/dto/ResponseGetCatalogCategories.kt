package com.ahinfo.ahteam.data.parser.catalogCategory.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseGetCatalogCategories(

	@field:SerializedName("summary")
	val summary: Int? = null,

	@field:SerializedName("elements")
	val elements: List<ElementsItem?>? = null,

	@field:SerializedName("page_count")
	val pageCount: Int? = null
)

data class ElementsItem(

	@field:SerializedName("section_id")
	val sectionId: Int? = null,

	@field:SerializedName("parsing_id")
	val parsingId: Int? = null,

	@field:SerializedName("root_name")
	val rootName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("active")
	val active: Any? = null,

	@field:SerializedName("href")
	val href: String? = null,

	@field:SerializedName("max_page")
	val maxPage: Int? = null
)
