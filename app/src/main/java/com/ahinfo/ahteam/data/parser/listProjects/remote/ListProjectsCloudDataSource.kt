package com.ahinfo.ahteam.data.parser.listProjects.remote

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.listProjects.remote.api.ListProjectsApi
import com.ahinfo.ahteam.data.parser.listProjects.remote.mapper.ListProjectDeleteDataToDomainMapper
import com.ahinfo.ahteam.data.parser.listProjects.remote.mapper.ListProjectsGetDataToDomainMapper
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectDeleteDomain
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsGetDomain
import javax.inject.Inject

class ListProjectsCloudDataSource @Inject constructor(
    private val api: ListProjectsApi,
    private val responseWrapper: ResponseWrapper,
    private val mapperGet: ListProjectsGetDataToDomainMapper,
    private val mapperDelete: ListProjectDeleteDataToDomainMapper
) {

    suspend fun getListProjectsFromServer(
        pageNumber: Int,
        countElementOnPage: Int
    ): BaseResult<ListProjectsGetDomain, Failure> = responseWrapper.handleResponse(mapperGet) {
        api.getListProjects(pageNumber, countElementOnPage)
    }

    suspend fun deleteProjectFromServer(idProject: Int): BaseResult<ListProjectDeleteDomain, Failure> =
        responseWrapper.handleResponse(mapperDelete) {
            api.deleteProject(idProject)
        }
}

