package com.ahinfo.ahteam.ui.screens.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.domain.authorization.entity.AuthModelDomain
import com.ahinfo.ahteam.domain.authorization.useCase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val useCase: AuthUseCase
): ViewModel() {

    private var _authState = MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState

    init {
        getAuthData()
    }

    private fun getAuthData(){

        _authState.value = AuthState.Loading

        viewModelScope.launch(Dispatchers.IO){

            val result = useCase.fetchAuthData()

            when(result){
                is BaseResult.Error -> {
                    if(result.err.code != 0){
                        _authState.postValue(AuthState.Error(result.err.message))
                    } else {
                        _authState.postValue(AuthState.NoInternet(result.err.message))
                    }
                }
                is BaseResult.Success -> _authState.postValue(AuthState.Success(result.data))
            }
        }
    }
}

sealed class AuthState {

    object Loading : AuthState()
    class Success(val data: AuthModelDomain) : AuthState()
    class Error(val messageError: String) : AuthState()
    class NoInternet(val messageNoInternet: String) : AuthState()
}