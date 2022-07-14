package com.ahinfo.ahteam.data.parser.listProjects.remote

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ResponseListProjects
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsDomain
import javax.inject.Inject

class ListProjectsDataToDomainMapper @Inject constructor() :
    Mapper<ResponseListProjects, ListProjectsDomain> {
    override fun map(data: ResponseListProjects): ListProjectsDomain {
        return ListProjectsDomain(
            data.response?.summary,
            data.response?.elements,
            data.response?.pageCount
        )
    }
}