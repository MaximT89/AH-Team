package com.ahinfo.ahteam.data.parser.updateProject

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.updateProject.remote.UpgradeProjectDataSource
import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.RequestUpgradeProject
import com.ahinfo.ahteam.domain.parser.updateProject.entity.UpgradeProjectDomain
import com.ahinfo.ahteam.domain.parser.updateProject.repository.UpgradeProjectRepository
import javax.inject.Inject

class UpgradeProjectRepositoryImpl @Inject constructor(
    private val upgradeProjectDataSource: UpgradeProjectDataSource
) : UpgradeProjectRepository {

    override suspend fun upgradeProject(requestUpgradeProject: RequestUpgradeProject): BaseResult<UpgradeProjectDomain, Failure> {
        return upgradeProjectDataSource.upgradeProject(requestUpgradeProject)
    }
}