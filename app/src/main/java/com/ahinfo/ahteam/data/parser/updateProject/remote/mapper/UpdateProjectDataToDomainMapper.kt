package com.ahinfo.ahteam.data.parser.updateProject.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.ResponseUpdateProject
import com.ahinfo.ahteam.domain.parser.updateProject.entity.UpdateProjectDomain
import javax.inject.Inject

class UpdateProjectDataToDomainMapper @Inject constructor() : Mapper<ResponseUpdateProject, UpdateProjectDomain> {
    override fun map(data: ResponseUpdateProject): UpdateProjectDomain {
        return UpdateProjectDomain(data.result)
    }
}