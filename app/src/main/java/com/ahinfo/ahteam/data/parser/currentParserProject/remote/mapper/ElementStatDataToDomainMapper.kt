package com.ahinfo.ahteam.data.parser.currentParserProject.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.ResponseGetElementStat
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetElementStatDomain
import javax.inject.Inject

class ElementStatDataToDomainMapper @Inject constructor() :
    Mapper<ResponseGetElementStat, GetElementStatDomain> {
    override fun map(data: ResponseGetElementStat): GetElementStatDomain {

        return GetElementStatDomain(
            countElements = data.stat?.countElements ?: 0,
            countOffers = data.stat?.countOffers ?: 0,
            countStore = data.stat?.countStore ?: 0,
            liveThread = data.workers?.liveThread
        )
    }
}