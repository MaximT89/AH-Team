package com.ahinfo.ahteam.domain.parser.updateTaskProject.useCase

import com.ahinfo.ahteam.data.parser.updateTaskProject.remote.dto.RequestUpdateProjectTask
import com.ahinfo.ahteam.domain.parser.updateTaskProject.repository.UpdateProjectTaskRepository
import javax.inject.Inject

class UpdateProjectTaskUseCase @Inject constructor(private val repository: UpdateProjectTaskRepository) {
    suspend fun updateProjectTask(request: RequestUpdateProjectTask) =
        repository.updateProject(request)
}