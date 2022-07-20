package com.ahinfo.ahteam.domain.parser.listProjects.repositoty

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectDeleteDomain
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsGetDomain

interface ListProjectsRepository {

    suspend fun getListProjects(
        pageNumber: Int,
        countElementOnPage: Int
    ) : BaseResult<ListProjectsGetDomain, Failure>

    suspend fun deleteProject(
        idProject: Int
    ) : BaseResult<ListProjectDeleteDomain, Failure>

    fun loadPage() : Int

    fun savePage(value : Int)

    fun loadCountProjectsOnPage() : Int

    fun saveCountProjectsOnPage(value : Int)

    fun returnToDefaultSettings()
}