package com.ahinfo.ahteam.data.parser.catalogElementsLinks

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.local.ElementsLinksPrefs
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.cloudDataSource.ElementsLinksCloudDataSource
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestElementsLinks
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestElementsLinksFilter
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestElementsLinksStat
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.ElementsLinksDomain
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.ElementsLinksFilterDomain
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.ElementsLinksStatDomain
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.repository.ElementsLinksRepository
import javax.inject.Inject

class ElementsLinksRepositoryImpl @Inject constructor(
    private val cloudDataSource : ElementsLinksCloudDataSource,
    private val localDataSource : ElementsLinksPrefs
): ElementsLinksRepository {

    override suspend fun getElementsLinksCatalog(request: RequestElementsLinks): BaseResult<ElementsLinksDomain, Failure> {
        return cloudDataSource.getElementsLinks(request)
    }

    override suspend fun getElementsLinksFilter(request: RequestElementsLinksFilter): BaseResult<ElementsLinksFilterDomain, Failure> {
        return cloudDataSource.getElementsLinksFilter(request)
    }

    override suspend fun getElementsLinksStat(request: RequestElementsLinksStat): BaseResult<ElementsLinksStatDomain, Failure> {
        return cloudDataSource.getElementsLinksStat(request)
    }

    override fun loadCatalogPage(): Int {
        return localDataSource.loadNumberPage()
    }

    override fun saveCatalogPage(page: Int) {
        localDataSource.saveNumberPage(page)
    }

    override fun loadCountItemOnPage(): Int {
        return localDataSource.loadCountElementOnPage()
    }

    override fun saveCountItemOnPage(countItem: Int) {
        localDataSource.saveCountElementOnPage(countItem)
    }

    override fun loadParserId(): Int {
        return localDataSource.loadParserId()
    }

    override fun saveParserId(parserId: Int) {
        localDataSource.saveParserId(parserId)
    }
}