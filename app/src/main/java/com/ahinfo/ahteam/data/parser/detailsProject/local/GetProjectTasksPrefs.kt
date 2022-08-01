package com.ahinfo.ahteam.data.parser.detailsProject.local

import com.ahinfo.ahteam.core.bases.BaseSharedPreferences
import com.ahinfo.ahteam.core.extension.editMe
import com.ahinfo.ahteam.core.extension.put
import javax.inject.Inject

class GetProjectTasksPrefs @Inject constructor(private val baseSharedPreferences: BaseSharedPreferences) {

    companion object {
        const val NUMBER_PAGE_TASK = "number_page_task"
        const val DEFAULT_NUMBER_PAGE_TASK = 1

        const val COUNT_PROJECTS_ON_PAGE_TASK = "count_project_on_page_task"
        const val DEFAULT_COUNT_PROJECTS_ON_PAGE = 1000

        const val PROJECT_ID = "project_id"
        const val DEFAULT_PROJECT_ID = 0
    }

    fun loadNumberPage() =
        baseSharedPreferences.defaultPref().getInt(NUMBER_PAGE_TASK, DEFAULT_NUMBER_PAGE_TASK)

    fun saveNumberPage(value: Int) {
        baseSharedPreferences.defaultPref().editMe { it.put(NUMBER_PAGE_TASK to value) }
    }

    fun loadCountElementOnPage() = baseSharedPreferences.defaultPref().getInt(
        COUNT_PROJECTS_ON_PAGE_TASK,
        DEFAULT_COUNT_PROJECTS_ON_PAGE
    )

    fun saveCountElementOnPage(value: Int) {
        baseSharedPreferences.defaultPref().editMe { it.put(COUNT_PROJECTS_ON_PAGE_TASK to value) }
    }

    fun saveProjectId(value: Int){
        baseSharedPreferences.defaultPref().editMe { it.put(PROJECT_ID to value) }
    }

    fun loadProjectId() = baseSharedPreferences.defaultPref().getInt(PROJECT_ID, DEFAULT_PROJECT_ID)
}