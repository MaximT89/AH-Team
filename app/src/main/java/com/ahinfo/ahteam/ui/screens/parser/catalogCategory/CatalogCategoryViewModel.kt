package com.ahinfo.ahteam.ui.screens.parser.catalogCategory

import androidx.lifecycle.ViewModel
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseViewModel
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.domain.parser.currentParserProject.useCase.TaskStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatalogCategoryViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val taskStatusUseCase: TaskStatusUseCase
): BaseViewModel() {

    override fun title() = resourceProvider.string(R.string.catalog_category_title)


}