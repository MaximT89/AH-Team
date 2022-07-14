package com.ahinfo.ahteam.domain.parser.listProjects.repositoty

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsDomain

interface ListProjectsRepository {

    suspend fun getListProjects(
        pageNumber: Int,
        countElementOnPage: Int
    ) : BaseResult<ListProjectsDomain, Failure>
}