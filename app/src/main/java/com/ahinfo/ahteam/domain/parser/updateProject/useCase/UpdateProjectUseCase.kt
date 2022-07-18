package com.ahinfo.ahteam.domain.parser.updateProject.useCase

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.RequestUpdateProject
import com.ahinfo.ahteam.domain.parser.updateProject.entity.UpdateProjectDomain
import com.ahinfo.ahteam.domain.parser.updateProject.repository.UpdateProjectRepository
import javax.inject.Inject

class UpdateProjectUseCase @Inject constructor(private val repository: UpdateProjectRepository) {

    suspend fun upgradeProject(requestUpdateProject: RequestUpdateProject): BaseResult<UpdateProjectDomain, Failure> {
        return repository.updateProject(requestUpdateProject)
    }
}