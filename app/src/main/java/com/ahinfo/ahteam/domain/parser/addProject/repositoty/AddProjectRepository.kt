package com.ahinfo.ahteam.domain.parser.addProject.repositoty

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.addProject.remote.dto.RequestAddProject
import com.ahinfo.ahteam.domain.parser.addProject.entity.AddProjectDomain

interface AddProjectRepository {

    suspend fun addProject(requestAddProject: RequestAddProject): BaseResult<AddProjectDomain, Failure>
}