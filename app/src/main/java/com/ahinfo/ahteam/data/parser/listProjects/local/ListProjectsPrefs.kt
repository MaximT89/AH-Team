package com.ahinfo.ahteam.data.parser.listProjects.local

import com.ahinfo.ahteam.core.bases.BaseSharedPreferences
import com.ahinfo.ahteam.core.extension.editMe
import com.ahinfo.ahteam.core.extension.put
import javax.inject.Inject

class ListProjectsPrefs @Inject constructor(private val baseSharedPreferences: BaseSharedPreferences) {

    companion object {
        const val NUMBER_PAGE_PROJECT = "number_page_project"
        const val COUNT_PROJECTS_ON_PAGE_PROJECT = "count_project_on_page_project"

        const val DEFAULT_NUMBER_PAGE_PROJECT = 1
        const val DEFAULT_COUNT_PROJECTS_ON_PAGE_PROJECT = 1000
    }

    fun loadNumberPage() =
        baseSharedPreferences.defaultPref().getInt(NUMBER_PAGE_PROJECT, DEFAULT_NUMBER_PAGE_PROJECT)

    fun saveNumberPage(value: Int) {
        baseSharedPreferences.defaultPref().editMe { it.put(NUMBER_PAGE_PROJECT to value) }
    }

    fun loadCountElementOnPage() = baseSharedPreferences.defaultPref()
        .getInt(COUNT_PROJECTS_ON_PAGE_PROJECT, DEFAULT_COUNT_PROJECTS_ON_PAGE_PROJECT)

    fun saveCountElementOnPage(value: Int) {
        baseSharedPreferences.defaultPref()
            .editMe { it.put(COUNT_PROJECTS_ON_PAGE_PROJECT to value) }
    }
}