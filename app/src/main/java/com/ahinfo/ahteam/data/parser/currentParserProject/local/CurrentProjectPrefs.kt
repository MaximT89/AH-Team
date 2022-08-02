package com.ahinfo.ahteam.data.parser.currentParserProject.local

import com.ahinfo.ahteam.core.bases.BaseSharedPreferences
import com.ahinfo.ahteam.core.extension.editMe
import com.ahinfo.ahteam.core.extension.put
import com.ahinfo.ahteam.data.parser.listProjects.local.ListProjectsPrefs
import javax.inject.Inject

class CurrentProjectPrefs @Inject constructor(private val baseSharedPreferences: BaseSharedPreferences) {

    companion object {
        const val CURRENT_PROJECT_ID = "current_project_id"
        const val CURRENT_TASK_ID = "current_task_id"

        const val DEFAULT_CURRENT_PROJECT_ID = 0
        const val DEFAULT_CURRENT_TASK_ID = 0

        const val NUMBER_PAGE_PROJECT = "number_page_project"
        const val COUNT_PROJECTS_ON_PAGE_PROJECT = "count_project_on_page_project"

        const val DEFAULT_NUMBER_PAGE_PROJECT = 1
        const val DEFAULT_COUNT_PROJECTS_ON_PAGE_PROJECT = 1000
    }

    fun loadCurrentProjectId() = baseSharedPreferences.defaultPref().getInt(
        CURRENT_PROJECT_ID,
        DEFAULT_CURRENT_PROJECT_ID
    )

    fun loadCurrentTaskId() = baseSharedPreferences.defaultPref().getInt(
        CURRENT_TASK_ID,
        DEFAULT_CURRENT_TASK_ID
    )

    fun saveCurrentProjectId(value : Int) {
        baseSharedPreferences.defaultPref().editMe { it.put(CURRENT_PROJECT_ID to value) }
    }

    fun saveCurrentTaskId(value : Int) {
        baseSharedPreferences.defaultPref().editMe { it.put(CURRENT_TASK_ID to value) }
    }

    fun loadNumberPage() =
        baseSharedPreferences.defaultPref().getInt(
            NUMBER_PAGE_PROJECT,
            DEFAULT_NUMBER_PAGE_PROJECT
        )

    fun saveNumberPage(value: Int) {
        baseSharedPreferences.defaultPref().editMe { it.put(NUMBER_PAGE_PROJECT to value) }
    }

    fun loadCountElementOnPage() = baseSharedPreferences.defaultPref()
        .getInt(
            COUNT_PROJECTS_ON_PAGE_PROJECT,
            DEFAULT_COUNT_PROJECTS_ON_PAGE_PROJECT
        )

    fun saveCountElementOnPage(value: Int) {
        baseSharedPreferences.defaultPref()
            .editMe { it.put(COUNT_PROJECTS_ON_PAGE_PROJECT to value) }
    }
}