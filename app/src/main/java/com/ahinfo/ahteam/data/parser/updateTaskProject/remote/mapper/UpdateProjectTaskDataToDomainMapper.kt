package com.ahinfo.ahteam.data.parser.updateTaskProject.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.updateTaskProject.remote.dto.ResponseUpdateProjectTask
import com.ahinfo.ahteam.domain.parser.updateTaskProject.entity.UpdateProjectTaskDomain
import javax.inject.Inject

class UpdateProjectTaskDataToDomainMapper @Inject constructor() : Mapper<ResponseUpdateProjectTask, UpdateProjectTaskDomain> {
    override fun map(data: ResponseUpdateProjectTask): UpdateProjectTaskDomain {
        return UpdateProjectTaskDomain(data.result!!)
    }
}