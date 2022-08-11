package com.ahinfo.ahteam.data.parser.addProjectTask

import com.ahinfo.ahteam.data.parser.addProjectTask.remote.AddProjectTaskCloudDataSource
import com.ahinfo.ahteam.data.parser.addProjectTask.remote.dto.RequestAddProjectTask
import com.ahinfo.ahteam.domain.parser.addProjectTask.repositoty.AddProjectTaskRepository
import javax.inject.Inject

class AppProjectTaskRepositoryImpl @Inject constructor(
    private val cloudDataSource: AddProjectTaskCloudDataSource
) : AddProjectTaskRepository {

    override suspend fun addProjectTask(request: RequestAddProjectTask) =
        cloudDataSource.addProject(request)
}