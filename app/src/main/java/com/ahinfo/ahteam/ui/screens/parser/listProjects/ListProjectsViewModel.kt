package com.ahinfo.ahteam.ui.screens.parser.listProjects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.bases.BaseViewModel
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsGetDomain
import com.ahinfo.ahteam.domain.parser.listProjects.useCases.ListProjectsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ListProjectsViewModel @Inject constructor(
    private val useCase: ListProjectsUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {
    override fun title(): String = resourceProvider.string(R.string.title_list_project)

    private var _listProjectState = MutableLiveData<ListProjectsState>()
    val listProjectState: LiveData<ListProjectsState> = _listProjectState

    init {
        // TODO: вытащить данные в префы и брать оттуда
        updateListProjectsData(1, 10)
    }

    fun updateListProjectsData(numberPage: Int, countProjectsOnPage: Int) {
        _listProjectState.value = ListProjectsState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            getListProjects(numberPage, countProjectsOnPage)
        }
    }

    fun deleteProject(idProject: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = useCase.deleteProject(idProject)) {
                is BaseResult.Error -> {
                    if (result.err.code != 0) _listProjectState.postValue(ListProjectsState.ErrorDeleteProject)
                    else _listProjectState.postValue(ListProjectsState.NoInternet(result.err.message))
                }
                is BaseResult.Success -> {
                    if (result.data.result == true) {
                        _listProjectState.postValue(ListProjectsState.SuccessDeleteProject)
                        getListProjects(1, 10)
                    } else _listProjectState.postValue(ListProjectsState.ErrorDeleteProject)
                }
            }
        }
    }

    private suspend fun getListProjects(numberPage: Int, countProjectsOnPage: Int) =
        withContext(Dispatchers.IO) {
            when (val result = useCase.getListProjects(numberPage, countProjectsOnPage)) {
                is BaseResult.Error -> {
                    if (result.err.code != 0) _listProjectState.postValue(
                        ListProjectsState.Error(
                            result.err.message
                        )
                    )
                    else _listProjectState.postValue(ListProjectsState.NoInternet(result.err.message))
                }
                is BaseResult.Success -> _listProjectState.postValue(
                    ListProjectsState.Success(
                        result.data
                    )
                )
            }
        }


}

sealed class ListProjectsState {

    object Loading : ListProjectsState()
    class Success(val data: ListProjectsGetDomain) : ListProjectsState()
    class Error(val messageError: String, val messageCode: Int? = null) : ListProjectsState()
    class NoInternet(val messageNoInternet: String) : ListProjectsState()
    object ErrorDeleteProject : ListProjectsState()
    object SuccessDeleteProject : ListProjectsState()
}