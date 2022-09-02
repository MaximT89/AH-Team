package com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseCatalogElementsLinks(

	@field:SerializedName("summary")
	val summary: Int? = null,

	@field:SerializedName("stat")
	val stat: Stat? = null,

	@field:SerializedName("elements")
	val elements: List<ElementsItem?>? = null,

	@field:SerializedName("filters")
	val filters: Filters? = null,

	@field:SerializedName("sort")
	val sort: Sort? = null,

	@field:SerializedName("page_count")
	val pageCount: Int? = null
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

data class ValuesItem(

	@field:SerializedName("section_id")
	val sectionId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class Sort(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("order")
	val order: String? = null
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

data class Range(

	@field:SerializedName("min")
	val min: Int? = null,

	@field:SerializedName("max")
	val max: Int? = null
)

data class Filters(

	@field:SerializedName("exist")
	val exist: Exist? = null,

	@field:SerializedName("section_id")
	val sectionId: SectionId? = null,

	@field:SerializedName("price")
	val price: Price? = null
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

data class ElementsItem(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("manufacturer")
	val manufacturer: String? = null,

	@field:SerializedName("vstavka")
	val vstavka: String? = null,

	@field:SerializedName("section_id")
	val sectionId: Int? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("count_category")
	val countCategory: Int? = null,

	@field:SerializedName("href")
	val href: String? = null,

	@field:SerializedName("kar_weight")
	val karWeight: Any? = null,

	@field:SerializedName("height")
	val height: Any? = null,

	@field:SerializedName("pletenie")
	val pletenie: String? = null,

	@field:SerializedName("old_price")
	val oldPrice: Int? = null,

	@field:SerializedName("parsing_id")
	val parsingId: Int? = null,

	@field:SerializedName("length")
	val length: Any? = null,

	@field:SerializedName("metal")
	val metal: String? = null,

	@field:SerializedName("vid_obrabotki")
	val vidObrabotki: Any? = null,

	@field:SerializedName("weight")
	val weight: Double? = null,

	@field:SerializedName("element_id")
	val elementId: Int? = null,

	@field:SerializedName("mpn")
	val mpn: String? = null,

	@field:SerializedName("article")
	val article: String? = null,

	@field:SerializedName("exist")
	val exist: Int? = null,

	@field:SerializedName("proba")
	val proba: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("width")
	val width: Double? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("forma_osn_vstavki")
	val formaOsnVstavki: Any? = null,

	@field:SerializedName("price_with_card")
	val priceWithCard: Any? = null
)
