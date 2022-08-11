package com.ahinfo.ahteam.ui.screens.parser.addProjectTask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.bases.BaseViewModel
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.core.extension.log
import com.ahinfo.ahteam.data.parser.addProjectTask.remote.dto.RequestAddProjectTask
import com.ahinfo.ahteam.domain.parser.addProject.entity.AddProjectDomain
import com.ahinfo.ahteam.domain.parser.addProjectTask.entity.AddProjectTaskDomain
import com.ahinfo.ahteam.domain.parser.addProjectTask.useCases.AddProjectTaskUseCase
import com.ahinfo.ahteam.ui.screens.parser.addProject.AddProjectsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val useCase: AddProjectTaskUseCase
) : BaseViewModel() {
    override fun title(): String = resourceProvider.string(R.string.add_task)

    private var projectId = MutableLiveData(0)

    private var _addProjectTaskState = MutableLiveData<AddProjectTaskState>()
    val addProjectTaskState: LiveData<AddProjectTaskState> = _addProjectTaskState

    fun saveProjectId(value: Int) {
        projectId.value = value
    }

    fun loadProjectId() = projectId.value

    fun addProjectTask(request: RequestAddProjectTask) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _addProjectTaskState.value = AddProjectTaskState.Loading
            }
            when (val result = useCase.addProjectTask(request)) {
                is BaseResult.Error -> {
                    if (result.err.code == 1) addProjectTask(request)
                    else if (result.err.code != 0) _addProjectTaskState.postValue(
                        AddProjectTaskState.Error(
                            result.err.message
                        )
                    )
                    else _addProjectTaskState.postValue(AddProjectTaskState.NoInternet(result.err.message))
                }
                is BaseResult.Success -> _addProjectTaskState.postValue(
                    AddProjectTaskState.Success(
                        result.data
                    )
                )
            }
        }
    }
}

sealed class AddProjectTaskState {

    object Loading : AddProjectTaskState()
    class Success(val data: AddProjectTaskDomain) : AddProjectTaskState()
    class Error(val messageError: String, val messageCode: Int? = null) : AddProjectTaskState()
    class NoInternet(val messageNoInternet: String) : AddProjectTaskState()
}