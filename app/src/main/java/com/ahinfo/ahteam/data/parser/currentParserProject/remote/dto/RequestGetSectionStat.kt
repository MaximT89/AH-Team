package com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto

import com.google.gson.annotations.SerializedName

class RequestGetSectionStat(
    @SerializedName("parsing_id")
    val parsingId: Int,
)