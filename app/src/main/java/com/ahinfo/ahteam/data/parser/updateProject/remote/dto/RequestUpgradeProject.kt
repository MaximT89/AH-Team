package com.ahinfo.ahteam.data.parser.updateProject.remote.dto

import com.google.gson.annotations.SerializedName

data class RequestUpgradeProject(
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("description")
    val description: String? = null,
    @field:SerializedName("project_id")
    val projectId: Int? = null
)