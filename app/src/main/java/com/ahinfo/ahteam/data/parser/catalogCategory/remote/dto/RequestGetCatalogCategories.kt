package com.ahinfo.ahteam.data.parser.catalogCategory.remote.dto

import com.google.gson.annotations.SerializedName

data class RequestGetCatalogCategories(
    @SerializedName("parsing_id")
    val parsingId: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("count_items")
    val countItems: Int,
)


