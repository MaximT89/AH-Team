package com.ahinfo.ahteam.data.parser.currentParserProject.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.ResponseGetParserTaskStatus
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetParserTaskStatusDomain
import javax.inject.Inject

class GetParserTaskStatusDataToDomainMapper @Inject constructor() :
    Mapper<ResponseGetParserTaskStatus, GetParserTaskStatusDomain> {
    override fun map(data: ResponseGetParserTaskStatus): GetParserTaskStatusDomain {
        return GetParserTaskStatusDomain(data.elements?.get(0)?.status)
    }
}