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
import com.ahinfo.ahteam.domain.parser.addProject.entity.AddProjectDomain
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetParserTaskStatusDomain
import com.ahinfo.ahteam.domain.parser.currentParserProject.useCase.GetParserTaskStatusUseCase
import com.ahinfo.ahteam.ui.screens.parser.listProjects.ListProjectsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentParserProjectViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val useCase: GetParserTaskStatusUseCase
) : BaseViewModel() {

    override fun title(): String = resourceProvider.string(R.string.title_current_parser_project)

    private var _currentParserState = MutableLiveData<CurrentParserState>()
    val currentParserState: LiveData<CurrentParserState> = _currentParserState

    init {
        _currentParserState.value = CurrentParserState.Loading
    }

    fun saveCurrentTaskId(itemTask: ElementsItemTask) {
        useCase.saveCurrentTaskId(itemTask.parsingId!!)
        useCase.saveCurrentProjectId(itemTask.projectId!!)
    }

    fun getCurrentTaskStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = useCase.getParserTaskStatus(
                RequestGetParserTaskStatus(
                    projectId = useCase.loadCurrentProjectId(),
                    page = useCase.loadPage(),
                    countItems = useCase.loadCountProjectsOnPage(),
                    parsingId = useCase.loadCurrentTaskId()
                )
            )) {
                is BaseResult.Error -> {
                    if (result.err.code != 0) _currentParserState.postValue(
                        CurrentParserState.Error(
                            result.err.message,
                            result.err.code
                        )
                    )
                    else _currentParserState.postValue(CurrentParserState.NoInternet(result.err.message))
                }
                is BaseResult.Success -> adapterParserStatus(result.data)
            }
        }
    }

    private fun adapterParserStatus(result: GetParserTaskStatusDomain) {
        when(result.status){
            ParserStatuses.PARSING_CREATE.status -> CurrentParserState.ParsingCreate
            ParserStatuses.MENU_START.status -> CurrentParserState.MenuStart
            ParserStatuses.MENU_COMPLETE.status -> CurrentParserState.MenuComplete
            ParserStatuses.CATALOG_START.status -> CurrentParserState.CatalogStart
            ParserStatuses.CATALOG_COMPLETE.status -> CurrentParserState.CatalogComplete
            ParserStatuses.ELEMENT_START.status -> CurrentParserState.ElementStart
            ParserStatuses.ELEMENT_COMPLETE.status -> CurrentParserState.ElementComplete
            ParserStatuses.MENU_ERROR.status -> CurrentParserState.MenuError
            ParserStatuses.CATALOG_ERROR.status -> CurrentParserState.CatalogError
            ParserStatuses.ELEMENT_ERROR.status -> CurrentParserState.ElementError
            null -> CurrentParserState.Error("Нет статуса")
        }
    }
}

sealed class CurrentParserState {
    object Loading : CurrentParserState()
    class Error(val messageError: String, val messageCode: Int? = null) : CurrentParserState()
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