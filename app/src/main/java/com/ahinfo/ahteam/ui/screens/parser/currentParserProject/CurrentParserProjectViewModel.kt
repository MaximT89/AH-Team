package com.ahinfo.ahteam.ui.screens.parser.currentParserProject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseViewModel
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.domain.parser.addProject.entity.AddProjectDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrentParserProjectViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
): BaseViewModel()  {

    override fun title(): String = resourceProvider.string(R.string.title_current_parser_project)

    private var _currentParserState = MutableLiveData<CurrentParserState>()
    val currentParserState : LiveData<CurrentParserState> = _currentParserState

    init {
        _currentParserState.value = CurrentParserState.Loading
    }
}

sealed class CurrentParserState {

    object Loading : CurrentParserState()

    // TODO: изменить модель
    class Success(val data: AddProjectDomain) : CurrentParserState()

    class Error(val messageError: String, val messageCode: Int? = null) : CurrentParserState()
    class NoInternet(val messageNoInternet: String) : CurrentParserState()
    class EmptyParser() : CurrentParserState()
}