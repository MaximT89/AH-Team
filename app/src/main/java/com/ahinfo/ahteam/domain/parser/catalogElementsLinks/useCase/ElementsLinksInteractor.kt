package com.ahinfo.ahteam.domain.parser.catalogElementsLinks.useCase

import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestElementsLinks
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestElementsLinksFilter
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestElementsLinksStat
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.repository.ElementsLinksRepository
import javax.inject.Inject

class ElementsLinksInteractor @Inject constructor(
    private val repository: ElementsLinksRepository
) {

    suspend fun getElementsLinksCatalog(request: RequestElementsLinks) =
        repository.getElementsLinksCatalog(request)

    suspend fun getElementsLinksFilter(request: RequestElementsLinksFilter) =
        repository.getElementsLinksFilter(request)

    suspend fun getElementsLinksStat(request: RequestElementsLinksStat) =
        repository.getElementsLinksStat(request)

    fun loadCatalogPage() = repository.loadCatalogPage()

    fun saveCatalogPage(page: Int) {
        repository.saveCatalogPage(page)
    }

    fun loadCountItemOnPage() = repository.loadCountItemOnPage()

    fun saveCountItemOnPage(countItem: Int) {
        repository.saveCountItemOnPage(countItem)
    }

    fun loadParserId() = repository.loadParserId()

    fun saveParserId(parserId: Int) {
        repository.saveParserId(parserId)
    }
}