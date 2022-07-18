package com.ahinfo.ahteam.domain.parser.updateProject.useCase

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.RequestUpgradeProject
import com.ahinfo.ahteam.domain.parser.updateProject.entity.UpgradeProjectDomain
import com.ahinfo.ahteam.domain.parser.updateProject.repository.UpgradeProjectRepository
import javax.inject.Inject

class UpgradeProjectUseCase @Inject constructor(private val repository: UpgradeProjectRepository) {

    suspend fun upgradeProject(requestUpgradeProject: RequestUpgradeProject): BaseResult<UpgradeProjectDomain, Failure> {
        return repository.upgradeProject(requestUpgradeProject)
    }
}