package com.ahinfo.ahteam.ui.screens.parser.listProjects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsDomain
import com.ahinfo.ahteam.domain.parser.listProjects.useCases.ListProjectsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListProjectsViewModel @Inject constructor(private val useCase: ListProjectsUseCase) :
    ViewModel() {

    private var _listProjectState = MutableLiveData<ListProjectsState>()
    val listProjectState: LiveData<ListProjectsState> = _listProjectState

    init {
        // TODO: вытащить данные в префы и брать оттуда
        updateListProjectsData(1, 10)
    }

    fun updateListProjectsData(numberPage: Int, countProjectsOnPage: Int) {
        _listProjectState.value = ListProjectsState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = useCase.getListProjects(numberPage, countProjectsOnPage)) {
                is BaseResult.Error -> {
                    if (result.err.code != 0) {
                        _listProjectState.postValue(ListProjectsState.Error(result.err.message))
                    } else {
                        _listProjectState.postValue(ListProjectsState.NoInternet(result.err.message))
                    }
                }
                is BaseResult.Success -> _listProjectState.postValue(ListProjectsState.Success(result.data))
            }
        }
    }
}

sealed class ListProjectsState {

    object Loading : ListProjectsState()
    class Success(val data: ListProjectsDomain) : ListProjectsState()
    class Error(val messageError: String, val messageCode: Int? = null) : ListProjectsState()
    class NoInternet(val messageNoInternet: String) : ListProjectsState()
}