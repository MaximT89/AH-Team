package com.ahinfo.ahteam.domain.parser.catalogElementsLinks.repository

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestElementsLinks
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestElementsLinksFilter
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.request.RequestElementsLinksStat
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.ElementsLinksDomain
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.ElementsLinksFilterDomain
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.ElementsLinksStatDomain

interface ElementsLinksRepository {

    suspend fun getElementsLinksCatalog(request : RequestElementsLinks) : BaseResult<ElementsLinksDomain, Failure>

    suspend fun getElementsLinksFilter(request : RequestElementsLinksFilter) : BaseResult<ElementsLinksFilterDomain, Failure>

    suspend fun getElementsLinksStat(request : RequestElementsLinksStat) : BaseResult<ElementsLinksStatDomain, Failure>

    fun loadCatalogPage() : Int

    fun saveCatalogPage(page : Int)

    fun loadCountItemOnPage() : Int

    fun saveCountItemOnPage(countItem : Int)

    fun loadParserId() : Int

    fun saveParserId(parserId : Int)
}