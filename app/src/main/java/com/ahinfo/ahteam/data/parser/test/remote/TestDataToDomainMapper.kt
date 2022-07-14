package com.ahinfo.ahteam.data.parser.test.remote

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.test.remote.dto.ResponseTestData
import com.ahinfo.ahteam.domain.parser.test.entity.TestModelDomain
import javax.inject.Inject

class TestDataToDomainMapper @Inject constructor() : Mapper<ResponseTestData, TestModelDomain> {
    override fun map(data: ResponseTestData): TestModelDomain =
        TestModelDomain(data.itemsLeft, data.totalItems)
}