package com.ahinfo.ahteam.data.parser.updateTaskProject.remote.dto

import com.google.gson.annotations.SerializedName

data class RequestUpdateProjectTask (
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("description")
    val description: String? = null,
    @field:SerializedName("parsing_id")
    val parsingId: Int? = null
)