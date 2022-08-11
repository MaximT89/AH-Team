package com.ahinfo.ahteam.data.parser.addProjectTask.remote.dto

import com.google.gson.annotations.SerializedName

data class RequestAddProjectTask(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("project_id")
    val projectId: Int? = null,
)