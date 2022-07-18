package com.ahinfo.ahteam.domain.parser.addProject.useCases

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.addProject.remote.dto.RequestAddProject
import com.ahinfo.ahteam.domain.parser.addProject.entity.AddProjectDomain
import com.ahinfo.ahteam.domain.parser.addProject.repositoty.AddProjectRepository
import javax.inject.Inject

class AddProjectUseCase @Inject constructor(
    private val repository: AddProjectRepository
) {

    suspend fun addProject(requestAddProject: RequestAddProject): BaseResult<AddProjectDomain, Failure> {
        return repository.addProject(requestAddProject)
    }

}