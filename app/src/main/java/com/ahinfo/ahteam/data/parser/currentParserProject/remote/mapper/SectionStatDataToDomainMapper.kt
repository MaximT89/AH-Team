package com.ahinfo.ahteam.data.parser.currentParserProject.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.ResponseGetSectionsStat
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetSectionStatDomain
import javax.inject.Inject

class SectionStatDataToDomainMapper @Inject constructor() : Mapper<ResponseGetSectionsStat, GetSectionStatDomain> {
    override fun map(data: ResponseGetSectionsStat): GetSectionStatDomain {
        return GetSectionStatDomain(
            avgPrice = data.stat?.avgPrice,
            avgWeight = data.stat?.avgWeight,
            countElements = data.stat?.countElements,
            exist = data.stat?.exist,
            maxPrice = data.stat?.maxPrice,
            minPrice = data.stat?.minPrice
        )
    }
}