package com.ahinfo.ahteam.domain.parser.detailsProject.useCases

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.RequestGetProjectTasks
import com.ahinfo.ahteam.domain.parser.detailsProject.entity.GetProjectTasksDomain
import com.ahinfo.ahteam.domain.parser.detailsProject.repositoty.GetProjectTasksRepository
import javax.inject.Inject

class GetProjectTasksUseCase @Inject constructor(private val repository: GetProjectTasksRepository) {

    suspend fun getProjectTasks(
        request: RequestGetProjectTasks
    ):
            BaseResult<GetProjectTasksDomain, Failure> =
        repository.getProjectTasks(request)


    fun loadPage(): Int = repository.loadPage()

    fun savePage(value: Int) {
        repository.savePage(value)
    }

    fun loadCountProjectsOnPage(): Int = repository.loadCountProjectsOnPage()

    fun saveCountProjectsOnPage(value: Int) {
        repository.saveCountProjectsOnPage(value)
    }

    fun returnToDefaultSettings() {
        repository.returnToDefaultSettings()
    }

    fun saveProjectIdInPrefs(projectId : Int) {
        repository.saveProjectId(projectId)
    }

    fun loadProjectIdFromPrefs() : Int = repository.loadProjectId()
}