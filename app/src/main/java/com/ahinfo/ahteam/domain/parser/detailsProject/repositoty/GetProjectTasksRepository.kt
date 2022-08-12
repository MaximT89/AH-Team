package com.ahinfo.ahteam.domain.parser.detailsProject.repositoty

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.RequestDeleteProjectTask
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.RequestGetProjectTasks
import com.ahinfo.ahteam.domain.parser.detailsProject.entity.DeleteProjectTaskDomain
import com.ahinfo.ahteam.domain.parser.detailsProject.entity.GetProjectTasksDomain

interface GetProjectTasksRepository {

    suspend fun getProjectTasks(
        request: RequestGetProjectTasks
    ): BaseResult<GetProjectTasksDomain, Failure>

    suspend fun deleteProjectTask(
        request: RequestDeleteProjectTask
    ): BaseResult<DeleteProjectTaskDomain, Failure>

    fun loadPage() : Int

    fun savePage(value : Int)

    fun loadCountProjectsOnPage() : Int

    fun saveCountProjectsOnPage(value : Int)

    fun returnToDefaultSettings()

    fun saveProjectId(projectId: Int)

    fun loadProjectId() : Int
}