package com.ahinfo.ahteam.data.parser.updateProject.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.ResponseUpdateProject
import com.ahinfo.ahteam.domain.parser.updateProject.entity.UpgradeProjectDomain
import javax.inject.Inject

class UpdateProjectDataToDomainMapper @Inject constructor() : Mapper<ResponseUpdateProject, UpgradeProjectDomain> {
    override fun map(data: ResponseUpdateProject): UpgradeProjectDomain {
        return UpgradeProjectDomain(data.result)
    }
}