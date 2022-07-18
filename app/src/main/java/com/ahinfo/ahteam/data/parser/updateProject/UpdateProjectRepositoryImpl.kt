package com.ahinfo.ahteam.data.parser.updateProject

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.updateProject.remote.UpdateProjectDataSource
import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.RequestUpdateProject
import com.ahinfo.ahteam.domain.parser.updateProject.entity.UpdateProjectDomain
import com.ahinfo.ahteam.domain.parser.updateProject.repository.UpdateProjectRepository
import javax.inject.Inject

class UpdateProjectRepositoryImpl @Inject constructor(
    private val updateProjectDataSource: UpdateProjectDataSource
) : UpdateProjectRepository {

    override suspend fun updateProject(requestUpdateProject: RequestUpdateProject): BaseResult<UpdateProjectDomain, Failure> {
        return updateProjectDataSource.updateProject(requestUpdateProject)
    }
}