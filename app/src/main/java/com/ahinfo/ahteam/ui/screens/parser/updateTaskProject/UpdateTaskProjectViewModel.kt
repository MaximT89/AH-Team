package com.ahinfo.ahteam.ui.screens.parser.updateTaskProject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.bases.BaseViewModel
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ElementsItemTask
import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ElementsItemProject
import com.ahinfo.ahteam.data.parser.updateTaskProject.remote.dto.RequestUpdateProjectTask
import com.ahinfo.ahteam.domain.parser.updateProject.entity.UpdateProjectDomain
import com.ahinfo.ahteam.domain.parser.updateTaskProject.entity.UpdateProjectTaskDomain
import com.ahinfo.ahteam.domain.parser.updateTaskProject.useCase.UpdateProjectTaskUseCase
import com.ahinfo.ahteam.ui.screens.parser.updateProject.UpdateProjectsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateTaskProjectViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val useCase: UpdateProjectTaskUseCase
) : BaseViewModel() {

    override fun title(): String = resourceProvider.string(R.string.update_task)

    private var _elementsItemTask = MutableLiveData<ElementsItemTask>()
    val elementsItemTask: LiveData<ElementsItemTask> = _elementsItemTask

    private var _updateProjectTaskState = MutableLiveData<UpdateProjectTaskState>()
    val updateProjectTaskState: LiveData<UpdateProjectTaskState> = _updateProjectTaskState

    fun updateElementItemTask(item: ElementsItemTask) {
        _elementsItemTask.value = item
    }

    fun updateProjectTask(request : RequestUpdateProjectTask){
        viewModelScope.launch(Dispatchers.IO){
            when(val result = useCase.updateProjectTask(request)){
                is BaseResult.Error -> {
                    if (result.err.code == 1) updateProjectTask(request)
                    else if (result.err.code != 0)
                        _updateProjectTaskState.postValue(UpdateProjectTaskState.Error(result.err.message))
                    else
                        _updateProjectTaskState.postValue(UpdateProjectTaskState.NoInternet(result.err.message))
                }
                is BaseResult.Success -> _updateProjectTaskState.postValue(
                    UpdateProjectTaskState.Success(
                        result.data
                    )
                )
            }
        }
    }
}

sealed class UpdateProjectTaskState {

    object Loading : UpdateProjectTaskState()
    class Success(val data: UpdateProjectTaskDomain) : UpdateProjectTaskState()
    class Error(val messageError: String, val messageCode: Int? = null) : UpdateProjectTaskState()
    class NoInternet(val messageNoInternet: String) : UpdateProjectTaskState()
}