package com.ahinfo.ahteam.domain.parser.listProjects.entity

import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ElementsItem

data class ListProjectsDomain (
    val summary: Int? = null,
    val elements: List<ElementsItem?>? = null,
    val pageCount: Int? = null
)