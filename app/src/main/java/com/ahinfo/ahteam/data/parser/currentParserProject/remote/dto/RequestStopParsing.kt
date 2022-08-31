package com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto

import com.google.gson.annotations.SerializedName

data class RequestStopParsing(
    @SerializedName("parsing_id")
    val parsingId: Int
)
