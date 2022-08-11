package com.ahinfo.ahteam.data.parser.addProjectTask.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.addProjectTask.remote.dto.ResponseAddProjectTask
import com.ahinfo.ahteam.domain.parser.addProjectTask.entity.AddProjectTaskDomain
import javax.inject.Inject

class AddProjectTaskDataToDomainMapper @Inject constructor(): Mapper<ResponseAddProjectTask, AddProjectTaskDomain> {
    override fun map(data: ResponseAddProjectTask): AddProjectTaskDomain {
        return AddProjectTaskDomain(data.result)
    }

}