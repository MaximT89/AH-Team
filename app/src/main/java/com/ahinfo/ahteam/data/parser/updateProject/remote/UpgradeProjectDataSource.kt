package com.ahinfo.ahteam.data.parser.updateProject.remote

import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.parser.updateProject.remote.api.UpgradeProjectApi
import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.RequestUpgradeProject
import com.ahinfo.ahteam.data.parser.updateProject.remote.mapper.UpdateProjectDataToDomainMapper
import javax.inject.Inject

class UpgradeProjectDataSource @Inject constructor(
    private val responseWrapper: ResponseWrapper,
    private val mapper: UpdateProjectDataToDomainMapper,
    private val api: UpgradeProjectApi
) {

    suspend fun upgradeProject(requestUpgradeProject: RequestUpgradeProject) =
        responseWrapper.handleResponse(mapper) {
            api.upgradeProject(requestUpgradeProject)
        }
}