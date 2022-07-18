package com.ahinfo.ahteam.domain.parser.updateProject.repository

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.RequestUpgradeProject
import com.ahinfo.ahteam.domain.parser.updateProject.entity.UpgradeProjectDomain

interface UpgradeProjectRepository {

    suspend fun upgradeProject(requestUpgradeProject: RequestUpgradeProject): BaseResult<UpgradeProjectDomain, Failure>
}