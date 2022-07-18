package com.ahinfo.ahteam.domain.parser.updateProject.repository

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.RequestUpdateProject
import com.ahinfo.ahteam.domain.parser.updateProject.entity.UpdateProjectDomain

interface UpdateProjectRepository {

    suspend fun updateProject(requestUpdateProject: RequestUpdateProject): BaseResult<UpdateProjectDomain, Failure>
}