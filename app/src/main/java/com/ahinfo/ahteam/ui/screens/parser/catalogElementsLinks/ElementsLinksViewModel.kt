package com.ahinfo.ahteam.ui.screens.parser.catalogElementsLinks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.bases.BaseViewModel
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.core.extension.log
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestElementsLinks
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.ElementsLinksDomain
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.ElementsLinksFilterDomain
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.ElementsLinksStatDomain
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.useCase.ElementsLinksInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ElementsLinksViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val interactor: ElementsLinksInteractor
) : BaseViewModel() {
    override fun title() = resourceProvider.string(R.string.elements_links_title)

    private var _elementsLinksState = MutableLiveData<ElementsLinksState>()
    val elementsLinksState: LiveData<ElementsLinksState> = _elementsLinksState

    private var _elementsLinksFilter = MutableLiveData<ElementsLinksFilterDomain?>()
    val elementsLinksFilter: LiveData<ElementsLinksFilterDomain?> = _elementsLinksFilter

    private var _maxPages = MutableLiveData(0)

    private var _currentPage = MutableLiveData(1)
    val currentPage: LiveData<Int> = _currentPage

    private var _canUpdateCatalog = MutableLiveData(false)
    val canUpdateCatalog: LiveData<Boolean> = _canUpdateCatalog

    /**
     * Функции для получения и сохранения ParserId
     */
    fun saveCurrentParserTaskId(id: Int) {
        interactor.saveParserId(id)
    }

    fun loadCurrentParserTaskId(): Int = interactor.loadParserId()

    /**
     * Функция для обновления переменной которая открывает возможность обновлять каталог
     */
    fun switchCanUpdateCatalog() {
        _canUpdateCatalog.value = !_canUpdateCatalog.value!!
    }

    /**
     * Сохраняем максимальную страницу каталога для текущего парсера
     */
    fun saveMaxPages(maxPage: Int) {
        _maxPages.value = maxPage
    }

    fun incPageAndAddItems() {
        if (_canUpdateCatalog.value == true) {
            _currentPage.value = _currentPage.value?.plus(1)

            if (_currentPage.value!! <= _maxPages.value!!) {
                switchCanUpdateCatalog()
                getElementsLinks()
            }
        }
    }

    /**
     * Функция для получения каталога для текущей страницы
     */
    fun getElementsLinks() {

        log("TAG1", "пошла загрузка")

        val request = RequestElementsLinks(
            filterLinks = _elementsLinksFilter.value,
            page = _currentPage.value,
            countItems = interactor.loadCountItemOnPage(),
            parsingId = interactor.loadParserId()
        )

        viewModelScope.launch(Dispatchers.IO) {
            loadingCatalog()
            when (val result = interactor.getElementsLinksCatalog(request)) {
                is BaseResult.Error -> {
                    if (result.err.code == 1) getElementsLinks()
                    else if (result.err.code != 0) _elementsLinksState.postValue(
                        ElementsLinksState.Error(
                            result.err.message
                        )
                    )
                    else _elementsLinksState.postValue(ElementsLinksState.NoInternet(result.err.message))
                }
                is BaseResult.Success -> {

                    withContext(Dispatchers.Main) { switchCanUpdateCatalog() }

                    _elementsLinksState.postValue(
                        ElementsLinksState.SuccessLoadCatalog(
                            result.data
                        )
                    )
                }
            }
        }
    }

    suspend fun loadingCatalog() = withContext(Dispatchers.Main) {
        _elementsLinksState.value = ElementsLinksState.LoadingCatalog
    }
}

/**
 * Статусы для каталога
 */
sealed class ElementsLinksState {

    object LoadingCatalog : ElementsLinksState()
    object LoadingFilter : ElementsLinksState()
    object LoadingStat : ElementsLinksState()
    class SuccessLoadCatalog(val data: ElementsLinksDomain) : ElementsLinksState()
    class SuccessLoadFilter(val data: ElementsLinksFilterDomain) : ElementsLinksState()
    class SuccessLoadStat(val data: ElementsLinksStatDomain) : ElementsLinksState()
    class Error(val messageError: String, val messageCode: Int? = null) : ElementsLinksState()
    class NoInternet(val messageNoInternet: String) : ElementsLinksState()
}