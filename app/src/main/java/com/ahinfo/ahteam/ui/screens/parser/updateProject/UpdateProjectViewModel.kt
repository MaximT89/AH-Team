package com.ahinfo.ahteam.ui.screens.parser.updateProject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.bases.BaseViewModel
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ElementsItem
import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.RequestUpdateProject
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsGetDomain
import com.ahinfo.ahteam.domain.parser.updateProject.entity.UpdateProjectDomain
import com.ahinfo.ahteam.domain.parser.updateProject.useCase.UpdateProjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateProjectViewModel @Inject constructor(
    private val useCase: UpdateProjectUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {
    override fun title(): String = resourceProvider.string(R.string.update_project)

    private var _elementsItem = MutableLiveData<ElementsItem>()
    val elementsItem: LiveData<ElementsItem> = _elementsItem

    private var _updateProjectState = MutableLiveData<UpdateProjectsState>()
    val updateProjectState: LiveData<UpdateProjectsState> = _updateProjectState

    fun updateElementsItem(item: ElementsItem) {
        _elementsItem.value = item
    }

    fun updateProject(requestUpdateProject: RequestUpdateProject) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = useCase.upgradeProject(requestUpdateProject)) {
                is BaseResult.Error -> {
                    if (result.err.code != 0) {
                        _updateProjectState.postValue(UpdateProjectsState.Error(result.err.message))
                    } else {
                        _updateProjectState.postValue(UpdateProjectsState.NoInternet(result.err.message))
                    }
                }
                is BaseResult.Success -> _updateProjectState.postValue(
                    UpdateProjectsState.Success(
                        result.data
                    )
                )
            }
        }
    }


}

sealed class UpdateProjectsState {

    object Loading : UpdateProjectsState()
    class Success(val data: UpdateProjectDomain) : UpdateProjectsState()
    class Error(val messageError: String, val messageCode: Int? = null) : UpdateProjectsState()
    class NoInternet(val messageNoInternet: String) : UpdateProjectsState()
}