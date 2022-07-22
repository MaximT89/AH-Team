package com.ahinfo.ahteam.ui.screens.parser.detailsProject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.bases.BaseViewModel
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.domain.parser.detailsProject.entity.GetProjectTasksDomain
import com.ahinfo.ahteam.domain.parser.detailsProject.useCases.GetProjectTasksUseCase
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsGetDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailProjectViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val useCase: GetProjectTasksUseCase
) : BaseViewModel() {
    override fun title(): String = resourceProvider.string(R.string.detail_fragment)

    private var _detailProjectState = MutableLiveData<DetailProjectState>()
    val detailProjectState: LiveData<DetailProjectState> = _detailProjectState

    private var _currentProject = MutableLiveData<Int>()
    val currentProject: LiveData<Int> = _currentProject

    fun updateUiProjectTasks(projectId: Int) {
        viewModelScope.launch {
            _detailProjectState.value = DetailProjectState.Loading
            getProjectTasks(projectId)
        }
    }

    suspend fun getProjectTasks(projectId: Int) =
        withContext(Dispatchers.IO) {
            when (val result = useCase.getProjectTasks(
                projectId,
                useCase.loadPage(),
                useCase.loadCountProjectsOnPage()
            )) {
                is BaseResult.Error -> {
                    if (result.err.code != 0) {
                        _detailProjectState.postValue(DetailProjectState.Error(result.err.message))
                    } else {
                        _detailProjectState.postValue(DetailProjectState.NoInternet(result.err.message))
                    }
                }
                is BaseResult.Success -> {
                    _detailProjectState.postValue(DetailProjectState.Success(result.data))
                }
            }
        }

}

sealed class DetailProjectState {

    object Loading : DetailProjectState()
    class Success(val data: GetProjectTasksDomain) : DetailProjectState()
    class Error(val messageError: String, val messageCode: Int? = null) : DetailProjectState()
    class NoInternet(val messageNoInternet: String) : DetailProjectState()
    object ErrorDeleteProject : DetailProjectState()
    object SuccessDeleteProject : DetailProjectState()
}