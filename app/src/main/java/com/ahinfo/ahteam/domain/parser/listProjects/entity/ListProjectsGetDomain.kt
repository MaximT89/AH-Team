package com.ahinfo.ahteam.domain.parser.listProjects.entity

import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ElementsItemProject

data class ListProjectsGetDomain (
    val summary: Int? = null,
    val elements: List<ElementsItemProject?>? = null,
    val pageCount: Int? = null
)