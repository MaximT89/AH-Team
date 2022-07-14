package com.ahinfo.ahteam.data.parser.listProjects

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.listProjects.remote.ListProjectsCloudDataSource
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsDomain
import com.ahinfo.ahteam.domain.parser.listProjects.repositoty.ListProjectsRepository
import javax.inject.Inject

class ListProjectsRepositoryImpl @Inject constructor(private val cloudDataSource: ListProjectsCloudDataSource) :
    ListProjectsRepository {

    override suspend fun getListProjects(
        pageNumber: Int,
        countElementOnPage: Int
    ): BaseResult<ListProjectsDomain, Failure> {
        return cloudDataSource.getListProjectsFromServer(
            pageNumber,
            countElementOnPage
        )
    }
}