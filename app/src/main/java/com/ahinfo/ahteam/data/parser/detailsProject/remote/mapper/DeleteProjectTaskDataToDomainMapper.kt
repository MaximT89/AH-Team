package com.ahinfo.ahteam.data.parser.detailsProject.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ResponseDeleteProjectTask
import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ResponseDeleteProject
import com.ahinfo.ahteam.domain.parser.detailsProject.entity.DeleteProjectTaskDomain
import javax.inject.Inject

class DeleteProjectTaskDataToDomainMapper @Inject constructor() :
    Mapper<ResponseDeleteProjectTask, DeleteProjectTaskDomain> {

    override fun map(data: ResponseDeleteProjectTask): DeleteProjectTaskDomain {
        return DeleteProjectTaskDomain(data.result!!)
    }
}