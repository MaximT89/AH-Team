package com.ahinfo.ahteam.ui.screens.project.sections

import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseViewModel
import com.ahinfo.ahteam.core.common.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SectionsViewModel @Inject constructor(private val resourceProvider: ResourceProvider): BaseViewModel() {
    override fun title(): String = resourceProvider.string(R.string.sections)
}