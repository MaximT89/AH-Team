package com.ahinfo.ahteam.data.parser.listProjects.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ResponseDeleteProject
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectDeleteDomain
import javax.inject.Inject

class ListProjectDeleteDataToDomainMapper @Inject constructor() :
    Mapper<ResponseDeleteProject, ListProjectDeleteDomain> {

    override fun map(data: ResponseDeleteProject): ListProjectDeleteDomain =
        ListProjectDeleteDomain(data.result)
}