package com.ahinfo.ahteam.data.parser.listProjects

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.listProjects.local.ListProjectsPrefs
import com.ahinfo.ahteam.data.parser.listProjects.remote.ListProjectsCloudDataSource
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectDeleteDomain
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsGetDomain
import com.ahinfo.ahteam.domain.parser.listProjects.repositoty.ListProjectsRepository
import javax.inject.Inject

class ListProjectsRepositoryImpl @Inject constructor(
    private val cloudDataSource: ListProjectsCloudDataSource,
    private val prefs: ListProjectsPrefs

    ) : ListProjectsRepository {

    override suspend fun getListProjects(
        pageNumber: Int,
        countElementOnPage: Int
    ): BaseResult<ListProjectsGetDomain, Failure> {
        return cloudDataSource.getListProjectsFromServer(
            pageNumber,
            countElementOnPage
        )
    }

    override suspend fun deleteProject(idProject: Int): BaseResult<ListProjectDeleteDomain, Failure> {
        return cloudDataSource.deleteProjectFromServer(idProject)
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
        prefs.saveNumberPage(ListProjectsPrefs.DEFAULT_NUMBER_PAGE)
        prefs.saveCountElementOnPage(ListProjectsPrefs.DEFAULT_COUNT_PROJECTS_ON_PAGE)
    }
}