package com.ahinfo.ahteam.data.parser.listProjects.local

import com.ahinfo.ahteam.core.bases.BaseSharedPreferences
import com.ahinfo.ahteam.core.extension.editMe
import com.ahinfo.ahteam.core.extension.put
import javax.inject.Inject

class ListProjectsPrefs @Inject constructor(private val baseSharedPreferences: BaseSharedPreferences) {

    companion object {
        const val NUMBER_PAGE = "number_page"
        const val COUNT_PROJECTS_ON_PAGE = "count_project_on_page"

        const val DEFAULT_NUMBER_PAGE = 1
        const val DEFAULT_COUNT_PROJECTS_ON_PAGE = 1000
    }

    fun loadNumberPage() = baseSharedPreferences.defaultPref().getInt(NUMBER_PAGE, 1)
    fun saveNumberPage(value: Int) {
        baseSharedPreferences.defaultPref().editMe { it.put(NUMBER_PAGE to value) }
    }

    fun loadCountElementOnPage() = baseSharedPreferences.defaultPref().getInt(COUNT_PROJECTS_ON_PAGE, 1000)
    fun saveCountElementOnPage(value: Int) {
        baseSharedPreferences.defaultPref().editMe { it.put(COUNT_PROJECTS_ON_PAGE to value) }
    }
}