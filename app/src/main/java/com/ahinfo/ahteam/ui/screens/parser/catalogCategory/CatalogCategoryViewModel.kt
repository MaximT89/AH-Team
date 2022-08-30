package com.ahinfo.ahteam.ui.screens.parser.catalogCategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.bases.BaseViewModel
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.data.parser.catalogCategory.remote.dto.RequestGetCatalogCategories
import com.ahinfo.ahteam.domain.parser.addProjectTask.entity.AddProjectTaskDomain
import com.ahinfo.ahteam.domain.parser.catalogCategory.entity.GetCatalogCategoriesDomain
import com.ahinfo.ahteam.domain.parser.catalogCategory.useCase.GetCatalogCategoriesUseCase
import com.ahinfo.ahteam.domain.parser.currentParserProject.useCase.TaskStatusUseCase
import com.ahinfo.ahteam.ui.screens.parser.addProject.AddProjectsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CatalogCategoryViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val taskStatusUseCase: TaskStatusUseCase,
    private val catalogCategoriesUseCase: GetCatalogCategoriesUseCase
) : BaseViewModel() {

    override fun title() = resourceProvider.string(R.string.catalog_category_title)

    private var _catalogCategoriesState = MutableLiveData<CatalogCategoriesState>()
    val catalogCategoriesState: LiveData<CatalogCategoriesState> = _catalogCategoriesState

    init {
        updateCatalogCategories()
    }

    fun updateCatalogCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _catalogCategoriesState.value = CatalogCategoriesState.Loading
            }
            loadCatalogCategoriesFromServer()
        }
    }

    fun loadCatalogCategoriesFromServer() {
        viewModelScope.launch(Dispatchers.IO) {

            // TODO: перекинуть текущую страницу и количество элементов в юз кейс (создать локал префы)
            when (val result = catalogCategoriesUseCase.getCatalogCategories(
                RequestGetCatalogCategories(
                    taskStatusUseCase.loadCurrentTaskId(),
                    1,
                    300
                )
            )) {
                is BaseResult.Error -> {
                    if (result.err.code == 1) loadCatalogCategoriesFromServer()
                    else if (result.err.code != 0) _catalogCategoriesState.postValue(
                        CatalogCategoriesState.Error(
                            result.err.message
                        )
                    )
                    else _catalogCategoriesState.postValue(CatalogCategoriesState.NoInternet(result.err.message))
                }
                is BaseResult.Success -> _catalogCategoriesState.postValue(
                    CatalogCategoriesState.Success(
                        result.data
                    )
                )
            }
        }
    }
}

sealed class CatalogCategoriesState {

    object Loading : CatalogCategoriesState()
    class Success(val data: GetCatalogCategoriesDomain) : CatalogCategoriesState()
    class Error(val messageError: String, val messageCode: Int? = null) : CatalogCategoriesState()
    class NoInternet(val messageNoInternet: String) : CatalogCategoriesState()
}