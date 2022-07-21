package com.ahinfo.ahteam.domain.parser.detailsProject.entity

import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ElementsItem

data class GetProjectTasksDomain(
    val elements: List<ElementsItem?>? = null
)