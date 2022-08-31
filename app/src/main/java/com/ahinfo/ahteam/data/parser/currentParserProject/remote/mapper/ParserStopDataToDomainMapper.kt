package com.ahinfo.ahteam.data.parser.currentParserProject.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.ResponseParserStop
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.ParserStopDomain
import javax.inject.Inject

class ParserStopDataToDomainMapper @Inject constructor() : Mapper<ResponseParserStop, ParserStopDomain> {
    override fun map(data: ResponseParserStop): ParserStopDomain {
        return ParserStopDomain(data.result!!)
    }
}