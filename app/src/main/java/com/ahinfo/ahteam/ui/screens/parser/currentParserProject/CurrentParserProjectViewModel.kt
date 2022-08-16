package com.ahinfo.ahteam.ui.screens.parser.currentParserProject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.bases.BaseViewModel
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.currentParserProject.local.ParserStatuses
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.RequestGetParserTaskStatus
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ElementsItemTask
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetElementStatDomain
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetSectionStatDomain
import com.ahinfo.ahteam.domain.parser.currentParserProject.useCase.StatUseCase
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
    private val statUseCase: StatUseCase
) : BaseViewModel() {

    override fun title(): String = resourceProvider.string(R.string.title_current_parser_project)

    private var _currentParserState = MutableLiveData<CurrentParserState>()
    val currentParserState: LiveData<CurrentParserState> = _currentParserState

    private var _sectionStat = MutableLiveData<GetSectionStatDomain?>()
    val sectionStat: LiveData<GetSectionStatDomain?> = _sectionStat

    private var _elementStat = MutableLiveData<GetElementStatDomain?>()
    val elementStat: LiveData<GetElementStatDomain?> = _elementStat

    init {
        _currentParserState.value = CurrentParserState.LoadingRoot
    }

    fun saveCurrentTaskId(itemTask: ElementsItemTask) {
        taskStatusUseCase.saveCurrentTaskId(itemTask.parsingId!!)
        taskStatusUseCase.saveCurrentProjectId(itemTask.projectId!!)
    }

    fun saveCurrentTaskId(itemTaskId: String) {
        taskStatusUseCase.saveCurrentTaskId(itemTaskId.toInt())
    }

    fun getSectionStatShareText() = "Средняя цена: ${_sectionStat.value?.avgPrice}\n" +
                "Средний вес: ${_sectionStat.value?.avgWeight}\n" +
                "Всего элементов: ${_sectionStat.value?.countElements}\n" +
                "В наличии: ${_sectionStat.value?.exist}\n" +
                "Мин. цена: ${_sectionStat.value?.minPrice}\n" +
                "Макс. цена: ${_sectionStat.value?.maxPrice}\n\n" +
                "https://www.ah-team.com/parser/${taskStatusUseCase.loadCurrentTaskId()}"

    fun getTaskElementStat() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _currentParserState.postValue(CurrentParserState.LoadingElementStat)
            }
            when (val result = statUseCase.getElementStat(taskStatusUseCase.loadCurrentTaskId())) {
                is BaseResult.Error -> errorResult(result) { getTaskElementStat() }
                is BaseResult.Success -> {
                    _elementStat.postValue(result.data)
                    _currentParserState.postValue(
                        CurrentParserState.SuccessLoadElementStat(result.data)
                    )
                }
            }
        }
    }

    fun getTaskSectionStat() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _currentParserState.postValue(CurrentParserState.LoadingSectionStat)
            }
            when (val result = statUseCase.getSectionStat(taskStatusUseCase.loadCurrentTaskId())) {
                is BaseResult.Error -> errorResult(result) { getTaskSectionStat() }
                is BaseResult.Success -> {
                    _sectionStat.postValue(result.data)
                    _currentParserState.postValue(
                        CurrentParserState.SuccessLoadSectionStat(result.data)
                    )
                }
            }
        }
    }

    private fun errorResult(result: BaseResult.Error<Failure>, repeatCallBack: () -> Unit) {
        if (result.err.code == 1)
            repeatCallBack.invoke()
        else if (result.err.code != 0) {
            _currentParserState.postValue(
                CurrentParserState.Error(
                    result.err.message,
                    result.err.code
                )
            )
        } else _currentParserState.postValue(CurrentParserState.NoInternet(result.err.message))
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
                is BaseResult.Error -> errorResult(result) { getCurrentTaskStatus() }
                is BaseResult.Success -> adapterParserStatus(result.data.status)
            }
        }
    }

    private fun adapterParserStatus(result: String?) {
        when (result) {
            ParserStatuses.PARSING_CREATE.status -> _currentParserState.postValue(
                CurrentParserState.ParsingCreate(result)
            )
            ParserStatuses.MENU_START.status -> _currentParserState.postValue(
                CurrentParserState.MenuStart(result)
            )
            ParserStatuses.MENU_COMPLETE.status -> _currentParserState.postValue(
                CurrentParserState.MenuComplete(result)
            )
            ParserStatuses.CATALOG_START.status -> _currentParserState.postValue(
                CurrentParserState.CatalogStart(result)
            )
            ParserStatuses.CATALOG_COMPLETE.status -> _currentParserState.postValue(
                CurrentParserState.CatalogComplete(result)
            )
            ParserStatuses.ELEMENT_START.status -> _currentParserState.postValue(
                CurrentParserState.ElementStart(result)
            )
            ParserStatuses.ELEMENT_COMPLETE.status -> _currentParserState.postValue(
                CurrentParserState.ElementComplete(result)
            )
            ParserStatuses.MENU_ERROR.status -> _currentParserState.postValue(
                CurrentParserState.MenuError(result)
            )
            ParserStatuses.CATALOG_ERROR.status -> _currentParserState.postValue(
                CurrentParserState.CatalogError(result)
            )
            ParserStatuses.ELEMENT_ERROR.status -> _currentParserState.postValue(
                CurrentParserState.ElementError(result)
            )
            else -> _currentParserState.postValue(CurrentParserState.Error("Нет статуса"))
        }
    }
}

sealed class CurrentParserState {
    object LoadingRoot : CurrentParserState()
    object LoadingSectionStat : CurrentParserState()
    object LoadingElementStat : CurrentParserState()
    class Error(val messageError: String, val messageCode: Int? = null) : CurrentParserState()
    class SuccessLoadSectionStat(val data: GetSectionStatDomain) : CurrentParserState()
    class SuccessLoadElementStat(val data: GetElementStatDomain) : CurrentParserState()
    class NoInternet(val messageNoInternet: String) : CurrentParserState()
    class ParsingCreate(val status: String) : CurrentParserState()
    class MenuStart(val status: String) : CurrentParserState()
    class MenuComplete(val status: String) : CurrentParserState()
    class CatalogStart(val status: String) : CurrentParserState()
    class CatalogComplete(val status: String) : CurrentParserState()
    class ElementStart(val status: String) : CurrentParserState()
    class ElementComplete(val status: String) : CurrentParserState()
    class MenuError(val status: String) : CurrentParserState()
    class CatalogError(val status: String) : CurrentParserState()
    class ElementError(val status: String) : CurrentParserState()
}