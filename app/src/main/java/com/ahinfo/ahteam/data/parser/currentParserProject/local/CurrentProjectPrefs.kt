package com.ahinfo.ahteam.data.parser.currentParserProject.local

import com.ahinfo.ahteam.core.bases.BaseSharedPreferences
import com.ahinfo.ahteam.core.extension.editMe
import com.ahinfo.ahteam.core.extension.put
import javax.inject.Inject

class CurrentProjectPrefs @Inject constructor(private val baseSharedPreferences: BaseSharedPreferences) {

    companion object {
        const val CURRENT_PROJECT_ID = "current_project_id"
        const val CURRENT_TASK_ID = "current_task_id"

        const val DEFAULT_CURRENT_PROJECT_ID = 0
        const val DEFAULT_CURRENT_TASK_ID = 0
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
}