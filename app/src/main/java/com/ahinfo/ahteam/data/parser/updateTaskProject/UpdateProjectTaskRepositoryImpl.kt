package com.ahinfo.ahteam.data.parser.updateTaskProject

import com.ahinfo.ahteam.data.parser.updateTaskProject.remote.UpdateProjectTaskCloudDataSource
import com.ahinfo.ahteam.data.parser.updateTaskProject.remote.dto.RequestUpdateProjectTask
import com.ahinfo.ahteam.domain.parser.updateTaskProject.repository.UpdateProjectTaskRepository
import javax.inject.Inject

class UpdateProjectTaskRepositoryImpl @Inject constructor(
    private val cloudDataSource: UpdateProjectTaskCloudDataSource
) : UpdateProjectTaskRepository {
    override suspend fun updateProject(request: RequestUpdateProjectTask) =
        cloudDataSource.updateProjectTask(request)
}