package com.ahinfo.ahteam.ui.screens.parser.testScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.domain.parser.test.entity.TestModelDomain
import com.ahinfo.ahteam.domain.parser.test.useCases.TestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(private val useCase: TestUseCase) : ViewModel() {

    private var _testState = MutableLiveData<TestState>()
    val testState: LiveData<TestState> = _testState

    private var _idProject = MutableLiveData(10)
    val idProject : LiveData<Int> = _idProject

    init {
        fetchTestDataFromServer(_idProject.value!!)
    }

    fun fetchTestDataFromServer(id : Int) {

        _testState.value = TestState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = useCase.fetchTestData1(id)) {
                is BaseResult.Error -> {
                    if (result.err.code != 0) {
                        _testState.postValue(TestState.Error(result.err.message, result.err.code))
                    } else {
                        _testState.postValue(TestState.NoInternet(result.err.message))
                    }
                }
                is BaseResult.Success -> _testState.postValue(TestState.Success(result.data))
            }
        }
    }
}

sealed class TestState {

    object Loading : TestState()
    class Success(val data: TestModelDomain) : TestState()
    class Error(val messageError: String, val messageCode : Int? = null) : TestState()
    class NoInternet(val messageNoInternet: String) : TestState()
}
