package com.ahinfo.ahteam.ui.screens.parser.currentParserProject

import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseViewModel
import com.ahinfo.ahteam.core.common.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrentParserProjectViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
): BaseViewModel()  {

    override fun title(): String = resourceProvider.string(R.string.title_current_parser_project)
}