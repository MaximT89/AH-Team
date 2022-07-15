package com.ahinfo.ahteam.data.parser.listProjects.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ResponseListProjects
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsGetDomain
import javax.inject.Inject

class ListProjectsGetDataToDomainMapper @Inject constructor() :
    Mapper<ResponseListProjects, ListProjectsGetDomain> {
    override fun map(data: ResponseListProjects): ListProjectsGetDomain {
        return ListProjectsGetDomain(
            data.summary,
            data.elements,
            data.pageCount
        )
    }
}