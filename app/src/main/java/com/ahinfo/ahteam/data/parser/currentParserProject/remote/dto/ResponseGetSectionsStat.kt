package com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseGetSectionsStat(

    @field:SerializedName("stat")
    val stat: Stat? = null,

    @field:SerializedName("elements_on_page")
    val elementsOnPage: Int? = null,

    @field:SerializedName("section_stat")
    val sectionStat: List<SectionStatItem?>? = null
)

data class Stat(

    @field:SerializedName("avg_price")
    val avgPrice: Int? = null,

    @field:SerializedName("avg_weight")
    val avgWeight: Double? = null,

    @field:SerializedName("count_elements")
    val countElements: Int? = null,

    @field:SerializedName("exist")
    val exist: Int? = null,

    @field:SerializedName("max_price")
    val maxPrice: Int? = null,

    @field:SerializedName("min_price")
    val minPrice: Int? = null
)

data class SectionStatItem(

    @field:SerializedName("section_id")
    val sectionId: Int? = null,

    @field:SerializedName("root_name")
    val rootName: String? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("href")
    val href: String? = null,

    @field:SerializedName("max_page")
    val maxPage: Int? = null
)
