package com.ahinfo.ahteam.data.parser.detailsProject.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ResponseGetProjectTasks
import com.ahinfo.ahteam.domain.parser.detailsProject.entity.GetProjectTasksDomain
import javax.inject.Inject

class GetProjectTasksDataToDomainMapper @Inject constructor() :
    Mapper<ResponseGetProjectTasks, GetProjectTasksDomain> {
    override fun map(data: ResponseGetProjectTasks): GetProjectTasksDomain {
        return GetProjectTasksDomain(data.elements)
    }
}