package com.ahinfo.ahteam.ui.screens.parser.currentParserProject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.bases.BaseViewModel
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.data.parser.currentParserProject.local.ParserStatuses
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.RequestGetParserTaskStatus
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ElementsItemTask
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetSectionStatDomain
import com.ahinfo.ahteam.domain.parser.currentParserProject.useCase.TaskSectionStatUseCase
import com.ahinfo.ahteam.domain.parser.currentParserProject.useCase.TaskStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CurrentParserProjectViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val taskStatusUseCase: TaskStatusUseCase,
    private val taskSectionStatUseCase: TaskSectionStatUseCase
) : BaseViewModel() {

    override fun title(): String = resourceProvider.string(R.string.title_current_parser_project)

    private var _currentParserState = MutableLiveData<CurrentParserState>()
    val currentParserState: LiveData<CurrentParserState> = _currentParserState

    init {
        _currentParserState.value = CurrentParserState.LoadingRoot
    }

    fun saveCurrentTaskId(itemTask: ElementsItemTask) {
        taskStatusUseCase.saveCurrentTaskId(itemTask.parsingId!!)
        taskStatusUseCase.saveCurrentProjectId(itemTask.projectId!!)
    }

    fun getTaskSectionStat() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                _currentParserState.postValue(CurrentParserState.LoadingSectionStat)
            }
            when (val result =
                taskSectionStatUseCase.getSectionStat(taskStatusUseCase.loadCurrentTaskId())) {
                is BaseResult.Error -> {
                    if (result.err.code == 1)
                        getTaskSectionStat()
                    else if (result.err.code != 0) {
                        _currentParserState.postValue(
                            CurrentParserState.Error(
                                result.err.message,
                                result.err.code
                            )
                        )
                    } else _currentParserState.postValue(CurrentParserState.NoInternet(result.err.message))
                }
                is BaseResult.Success -> _currentParserState.postValue(
                    CurrentParserState.SuccessLoadSectionStat(
                        result.data
                    )
                )
            }
        }
    }

    fun getCurrentTaskStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = taskStatusUseCase.getParserTaskStatus(
                RequestGetParserTaskStatus(
                    projectId = taskStatusUseCase.loadCurrentProjectId(),
                    page = taskStatusUseCase.loadPage(),
                    countItems = taskStatusUseCase.loadCountProjectsOnPage(),
                    parsingId = taskStatusUseCase.loadCurrentTaskId()
                )
            )) {
                is BaseResult.Error -> {
                    if (result.err.code == 1) getCurrentTaskStatus()
                    else if (result.err.code != 0)
                        _currentParserState.postValue(
                            CurrentParserState.Error(
                                result.err.message,
                                result.err.code
                            )
                        )
                    else _currentParserState.postValue(CurrentParserState.NoInternet(result.err.message))
                }
                is BaseResult.Success -> {
                    adapterParserStatus(result.data.status)
                }
            }
        }
    }

    private fun adapterParserStatus(result: String?) {
        when (result) {
            ParserStatuses.PARSING_CREATE.status -> _currentParserState.postValue(CurrentParserState.ParsingCreate)
            ParserStatuses.MENU_START.status -> _currentParserState.postValue(CurrentParserState.MenuStart)
            ParserStatuses.MENU_COMPLETE.status -> _currentParserState.postValue(CurrentParserState.MenuComplete)
            ParserStatuses.CATALOG_START.status -> _currentParserState.postValue(CurrentParserState.CatalogStart)
            ParserStatuses.CATALOG_COMPLETE.status -> _currentParserState.postValue(
                CurrentParserState.CatalogComplete
            )
            ParserStatuses.ELEMENT_START.status -> _currentParserState.postValue(CurrentParserState.ElementStart)
            ParserStatuses.ELEMENT_COMPLETE.status -> _currentParserState.postValue(
                CurrentParserState.ElementComplete
            )
            ParserStatuses.MENU_ERROR.status -> _currentParserState.postValue(CurrentParserState.MenuError)
            ParserStatuses.CATALOG_ERROR.status -> _currentParserState.postValue(CurrentParserState.CatalogError)
            ParserStatuses.ELEMENT_ERROR.status -> _currentParserState.postValue(CurrentParserState.ElementError)
            else -> _currentParserState.postValue(CurrentParserState.Error("Нет статуса"))
        }
    }
}

sealed class CurrentParserState {
    object LoadingRoot : CurrentParserState()
    object LoadingSectionStat : CurrentParserState()
    class Error(val messageError: String, val messageCode: Int? = null) : CurrentParserState()
    class SuccessLoadSectionStat(val data: GetSectionStatDomain) : CurrentParserState()
    class NoInternet(val messageNoInternet: String) : CurrentParserState()
    object ParsingCreate : CurrentParserState()
    object MenuStart : CurrentParserState()
    object MenuComplete : CurrentParserState()
    object CatalogStart : CurrentParserState()
    object CatalogComplete : CurrentParserState()
    object ElementStart : CurrentParserState()
    object ElementComplete : CurrentParserState()
    object MenuError : CurrentParserState()
    object CatalogError : CurrentParserState()
    object ElementError : CurrentParserState()
}