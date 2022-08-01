package com.ahinfo.ahteam.domain.parser.detailsProject.repositoty

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.domain.parser.detailsProject.entity.GetProjectTasksDomain

interface GetProjectTasksRepository {

    suspend fun getProjectTasks(
        idProject: Int,
        numberPage: Int,
        countItemOnPage: Int
    ): BaseResult<GetProjectTasksDomain, Failure>

    fun loadPage() : Int

    fun savePage(value : Int)

    fun loadCountProjectsOnPage() : Int

    fun saveCountProjectsOnPage(value : Int)

    fun returnToDefaultSettings()

    fun saveProjectId(projectId: Int)

    fun loadProjectId() : Int
}