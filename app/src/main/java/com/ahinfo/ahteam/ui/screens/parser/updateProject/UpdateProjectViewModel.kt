package com.ahinfo.ahteam.ui.screens.parser.updateProject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ElementsItem
import com.ahinfo.ahteam.domain.parser.updateProject.useCase.UpdateProjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateProjectViewModel @Inject constructor(private val useCase: UpdateProjectUseCase) :
    ViewModel() {

    private var _elementsItem = MutableLiveData<ElementsItem>()
    val elementsItem : LiveData<ElementsItem> = _elementsItem

    fun updateElementsItem(item : ElementsItem) {
        _elementsItem.value = item
    }
}