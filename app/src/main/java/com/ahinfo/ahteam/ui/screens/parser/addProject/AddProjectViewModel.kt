package com.ahinfo.ahteam.ui.screens.parser.addProject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.data.parser.addProject.remote.dto.RequestAddProject
import com.ahinfo.ahteam.domain.parser.addProject.entity.AddProjectDomain
import com.ahinfo.ahteam.domain.parser.addProject.useCases.AddProjectUseCase
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsGetDomain
import com.ahinfo.ahteam.ui.screens.parser.listProjects.ListProjectsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProjectViewModel @Inject constructor(
    private val useCase: AddProjectUseCase
) : ViewModel() {

    private var _addProjectState = MutableLiveData<AddProjectsState>()
    val addProjectState: LiveData<AddProjectsState> = _addProjectState

    fun addProject(addProjectRequest: RequestAddProject) {
        _addProjectState.value = AddProjectsState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = useCase.addProject(addProjectRequest)) {
                is BaseResult.Error -> {
                    if (result.err.code != 0) _addProjectState.postValue(
                        AddProjectsState.Error(
                            result.err.message
                        )
                    )
                    else _addProjectState.postValue(AddProjectsState.NoInternet(result.err.message))
                }
                is BaseResult.Success -> _addProjectState.postValue(AddProjectsState.Success(result.data))
            }
        }
    }
}

sealed class AddProjectsState {

    object Loading : AddProjectsState()
    class Success(val data: AddProjectDomain) : AddProjectsState()
    class Error(val messageError: String, val messageCode: Int? = null) : AddProjectsState()
    class NoInternet(val messageNoInternet: String) : AddProjectsState()
}
