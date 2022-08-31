package com.ahinfo.ahteam.ui.screens.parser.catalogElementsLinks

import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseViewModel
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.domain.parser.currentParserProject.useCase.TaskStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ElementsLinksViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val taskStatusUseCase: TaskStatusUseCase
) : BaseViewModel() {
    override fun title() = resourceProvider.string(R.string.elements_links_title)



}