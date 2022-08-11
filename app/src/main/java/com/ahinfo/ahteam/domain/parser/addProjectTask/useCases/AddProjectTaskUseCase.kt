package com.ahinfo.ahteam.domain.parser.addProjectTask.useCases

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.addProjectTask.remote.dto.RequestAddProjectTask
import com.ahinfo.ahteam.domain.parser.addProjectTask.entity.AddProjectTaskDomain
import com.ahinfo.ahteam.domain.parser.addProjectTask.repositoty.AddProjectTaskRepository
import javax.inject.Inject

class AddProjectTaskUseCase @Inject constructor(
    private val repository: AddProjectTaskRepository
) {

    suspend fun addProjectTask(request: RequestAddProjectTask): BaseResult<AddProjectTaskDomain, Failure> {
        return repository.addProjectTask(request)
    }
}