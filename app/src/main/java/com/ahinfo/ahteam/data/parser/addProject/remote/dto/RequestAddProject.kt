package com.ahinfo.ahteam.data.parser.addProject.remote.dto

import com.google.gson.annotations.SerializedName

data class RequestAddProject(
    @field:SerializedName("name")
    val name: String = "Новый проект",
    @field:SerializedName("description")
    val description: String = "Нет описания"
)