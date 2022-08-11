package com.ahinfo.ahteam.domain.parser.addProjectTask.repositoty

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.addProjectTask.remote.dto.RequestAddProjectTask
import com.ahinfo.ahteam.domain.parser.addProjectTask.entity.AddProjectTaskDomain

interface AddProjectTaskRepository {

    suspend fun addProjectTask(request: RequestAddProjectTask): BaseResult<AddProjectTaskDomain, Failure>
}