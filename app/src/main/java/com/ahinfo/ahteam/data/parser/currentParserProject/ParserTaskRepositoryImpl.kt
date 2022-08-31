package com.ahinfo.ahteam.data.parser.currentParserProject

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.currentParserProject.local.CurrentProjectPrefs
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.cloudDataSource.ParserManageCloudDataSource
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.cloudDataSource.ProjectTaskStatusCloudDataSource
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.cloudDataSource.StatCloudDataSource
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.RequestGetParserTaskStatus
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetElementStatDomain
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetParserTaskStatusDomain
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetSectionStatDomain
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.ParserStopDomain
import com.ahinfo.ahteam.domain.parser.currentParserProject.repository.ParserTaskRepository
import javax.inject.Inject

class ParserTaskRepositoryImpl @Inject constructor(
    private val taskStatusCloudDataSource: ProjectTaskStatusCloudDataSource,
    private val statCloudDataSource: StatCloudDataSource,
    private val parserManageCloudDataSource: ParserManageCloudDataSource,
    private val prefs: CurrentProjectPrefs
) : ParserTaskRepository {

    override suspend fun getParserTaskStatus(
        request: RequestGetParserTaskStatus): BaseResult<GetParserTaskStatusDomain, Failure> =
        taskStatusCloudDataSource.getProjectTaskStatus(request)

    override suspend fun getSectionStat(taskId: Int): BaseResult<GetSectionStatDomain, Failure> =
        statCloudDataSource.getTaskSectionStat(taskId)

    override suspend fun getElementStat(taskId: Int): BaseResult<GetElementStatDomain, Failure> =
        statCloudDataSource.getTaskElementStat(taskId)

    override suspend fun parserStop(taskId: Int): BaseResult<ParserStopDomain, Failure> =
        parserManageCloudDataSource.parserStop(taskId)

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