package com.ahinfo.ahteam.data.parser.detailsProject

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.detailsProject.local.GetProjectTasksPrefs
import com.ahinfo.ahteam.data.parser.detailsProject.remote.ProjectTasksCloudDataSource
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.RequestDeleteProjectTask
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.RequestGetProjectTasks
import com.ahinfo.ahteam.domain.parser.detailsProject.entity.DeleteProjectTaskDomain
import com.ahinfo.ahteam.domain.parser.detailsProject.entity.GetProjectTasksDomain
import com.ahinfo.ahteam.domain.parser.detailsProject.repositoty.GetProjectTasksRepository
import javax.inject.Inject

class GetProjectTasksRepositoryImpl @Inject constructor(
    private val cloudDataSource: ProjectTasksCloudDataSource,
    private val prefs: GetProjectTasksPrefs
) : GetProjectTasksRepository {

    override suspend fun getProjectTasks(
        request: RequestGetProjectTasks
    ): BaseResult<GetProjectTasksDomain, Failure> = cloudDataSource.getProjectTasks(request)

    override suspend fun deleteProjectTask(
        request: RequestDeleteProjectTask
    ): BaseResult<DeleteProjectTaskDomain, Failure> = cloudDataSource.deleteProjectTask(request)

    override fun loadPage(): Int = prefs.loadNumberPage()

    override fun savePage(value: Int) {
        prefs.saveNumberPage(value)
    }

    override fun loadCountProjectsOnPage(): Int = prefs.loadCountElementOnPage()

    override fun saveCountProjectsOnPage(value: Int) {
        prefs.saveCountElementOnPage(value)
    }

    override fun returnToDefaultSettings() {
        prefs.saveNumberPage(GetProjectTasksPrefs.DEFAULT_NUMBER_PAGE_TASK)
        prefs.saveCountElementOnPage(GetProjectTasksPrefs.DEFAULT_COUNT_PROJECTS_ON_PAGE)
    }

    override fun saveProjectId(projectId: Int) {
        prefs.saveProjectId(projectId)
    }

    override fun loadProjectId(): Int = prefs.loadProjectId()
}