package com.ahinfo.ahteam.domain.parser.updateTaskProject.repository

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.updateTaskProject.remote.dto.RequestUpdateProjectTask
import com.ahinfo.ahteam.domain.parser.updateTaskProject.entity.UpdateProjectTaskDomain

interface UpdateProjectTaskRepository {

    suspend fun updateProject(request: RequestUpdateProjectTask): BaseResult<UpdateProjectTaskDomain, Failure>
}