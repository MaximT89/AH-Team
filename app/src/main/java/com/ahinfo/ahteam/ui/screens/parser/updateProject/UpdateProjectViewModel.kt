package com.ahinfo.ahteam.ui.screens.parser.updateProject

import androidx.lifecycle.ViewModel
import com.ahinfo.ahteam.domain.parser.updateProject.useCase.UpgradeProjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateProjectViewModel @Inject constructor(private val useCase: UpgradeProjectUseCase) :
    ViewModel() {



}