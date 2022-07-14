package com.ahinfo.ahteam.data.parser.listProjects.remote

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.listProjects.remote.api.ListProjectsApi
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsDomain
import javax.inject.Inject

class ListProjectsCloudDataSource @Inject constructor(
    private val api: ListProjectsApi,
    private val responseWrapper: ResponseWrapper,
    private val mapper: ListProjectsDataToDomainMapper
) {

    suspend fun getListProjectsFromServer(
        pageNumber: Int,
        countElementOnPage: Int
    ): BaseResult<ListProjectsDomain, Failure> = responseWrapper.handleResponse(mapper) {
        api.getListProjects(pageNumber, countElementOnPage)
    }
}

