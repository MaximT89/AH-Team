package com.ahinfo.ahteam.data.parser.catalogElementsLinks.local

import com.ahinfo.ahteam.core.bases.BaseSharedPreferences
import com.ahinfo.ahteam.core.extension.editMe
import com.ahinfo.ahteam.core.extension.put
import javax.inject.Inject

class ElementsLinksPrefs @Inject constructor(private val baseSharedPreferences: BaseSharedPreferences) {

    companion object {
        const val NUMBER_PAGE_CATALOG_ELEMENTS = "number_page_catalog_elements"
        const val DEFAULT_NUMBER_PAGE_TASK_CATALOG_ELEMENTS = 1

        const val COUNT_ITEMS_ON_PAGE_CATALOG_ELEMENTS = "count_items_on_page_catalog_elements"
        const val DEFAULT_COUNT_ITEMS_ON_PAGE_CATALOG_ELEMENTS = 100

        const val PROJECT_ID_CATALOG_ELEMENTS = "project_id_catalog_elements"
        const val DEFAULT_PROJECT_ID_CATALOG_ELEMENTS = 0
    }

    fun loadNumberPage() =
        baseSharedPreferences.defaultPref().getInt(
            NUMBER_PAGE_CATALOG_ELEMENTS,
            DEFAULT_NUMBER_PAGE_TASK_CATALOG_ELEMENTS
        )

    fun saveNumberPage(value: Int) {
        baseSharedPreferences.defaultPref().editMe { it.put(NUMBER_PAGE_CATALOG_ELEMENTS to value) }
    }

    fun loadCountElementOnPage() = baseSharedPreferences.defaultPref().getInt(
        COUNT_ITEMS_ON_PAGE_CATALOG_ELEMENTS,
        DEFAULT_COUNT_ITEMS_ON_PAGE_CATALOG_ELEMENTS
    )

    fun saveCountElementOnPage(value: Int) {
        baseSharedPreferences.defaultPref()
            .editMe { it.put(COUNT_ITEMS_ON_PAGE_CATALOG_ELEMENTS to value) }
    }

    fun saveParserId(value: Int) {
        baseSharedPreferences.defaultPref().editMe { it.put(PROJECT_ID_CATALOG_ELEMENTS to value) }
    }

    fun loadParserId() = baseSharedPreferences.defaultPref().getInt(
        PROJECT_ID_CATALOG_ELEMENTS,
        DEFAULT_PROJECT_ID_CATALOG_ELEMENTS
    )
}