package com.ahinfo.ahteam.data.parser.currentParserProject

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.currentParserProject.local.CurrentProjectPrefs
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.cloudDataSource.GetProjectTaskStatusCloudDataSource
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.RequestGetParserTaskStatus
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetParserTaskStatusDomain
import com.ahinfo.ahteam.domain.parser.currentParserProject.repository.GetParserTaskStatusRepository
import javax.inject.Inject

class GetParserTaskStatusRepositoryImpl @Inject constructor(
    private val cloudDataSource: GetProjectTaskStatusCloudDataSource,
    private val prefs: CurrentProjectPrefs
) : GetParserTaskStatusRepository {

    override suspend fun getParserTaskStatus(
        request: RequestGetParserTaskStatus
    ): BaseResult<GetParserTaskStatusDomain, Failure> =
        cloudDataSource.getProjectTaskStatus(request)

    override fun loadCurrentProjectId(): Int = prefs.loadCurrentProjectId()

    override fun saveCurrentProjectId(value: Int) {
        prefs.saveCurrentProjectId(value)
    }

    override fun loadCurrentTaskId(): Int = prefs.loadCurrentTaskId()

    override fun saveCurrentTaskId(value: Int) {
        prefs.saveCurrentTaskId(value)
    }

    override fun loadPage(): Int = prefs.loadNumberPage()

    override fun savePage(value: Int) {
        prefs.saveNumberPage(value)
    }

    override fun loadCountProjectsOnPage(): Int = prefs.loadCountElementOnPage()

    override fun saveCountProjectsOnPage(value: Int) {
        prefs.saveCountElementOnPage(value)
    }

    override fun returnToDefaultSettings() {
        prefs.saveNumberPage(CurrentProjectPrefs.DEFAULT_NUMBER_PAGE_PROJECT)
        prefs.saveCountElementOnPage(CurrentProjectPrefs.DEFAULT_COUNT_PROJECTS_ON_PAGE_PROJECT)
    }
}