package com.ahinfo.ahteam.ui.screens.parser.addProject

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.bases.BaseViewModel
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.addProject.remote.dto.RequestAddProject
import com.ahinfo.ahteam.domain.parser.addProject.entity.AddProjectDomain
import com.ahinfo.ahteam.domain.parser.addProject.useCases.AddProjectUseCase
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsGetDomain
import com.ahinfo.ahteam.ui.screens.parser.listProjects.ListProjectsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.MutableLiveData as MutableLiveData

@HiltViewModel
class AddProjectViewModel @Inject constructor(
    private val useCase: AddProjectUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {

    private var _addProjectState = MutableLiveData<AddProjectsState>()
    val addProjectState: LiveData<AddProjectsState> = _addProjectState

    fun addProject(addProjectRequest: RequestAddProject) {
        _addProjectState.value = AddProjectsState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = useCase.addProject(addProjectRequest)) {
                is BaseResult.Error -> resultError(result, _addProjectState)
                is BaseResult.Success -> _addProjectState.postValue(AddProjectsState.Success(result.data))
            }
        }
    }

    private fun <T : MutableLiveData<AddProjectsState>>resultError(
        result: BaseResult.Error<Failure>,
        liveData: T,
    ) {
        if (result.err.code != 0) liveData.postValue(
            AddProjectsState.Error(result.err.message)
        )
        else liveData.postValue(AddProjectsState.NoInternet(result.err.message))
    }

    override fun title(): String = resourceProvider.string(R.string.add_project)
}

sealed class AddProjectsState {

    object Loading : AddProjectsState()
    class Success(val data: AddProjectDomain) : AddProjectsState()
    class Error(val messageError: String, val messageCode: Int? = null) : AddProjectsState()
    class NoInternet(val messageNoInternet: String) : AddProjectsState()
}


