package com.ahinfo.ahteam.data.parser.addProject.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.addProject.remote.dto.ResponseAddProject
import com.ahinfo.ahteam.domain.parser.addProject.entity.AddProjectDomain
import javax.inject.Inject

class AddProjectDataToDomainMapper @Inject constructor(): Mapper<ResponseAddProject, AddProjectDomain> {
    override fun map(data: ResponseAddProject): AddProjectDomain {
        return AddProjectDomain(data.result)
    }
}