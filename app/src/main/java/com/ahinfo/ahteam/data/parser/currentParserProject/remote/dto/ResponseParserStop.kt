package com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseParserStop(
    @field:SerializedName("result")
    val result: Boolean? = null
)