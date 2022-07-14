package com.ahinfo.ahteam.domain.parser.test.repository

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.domain.parser.test.entity.TestModelDomain

interface TestRepository {

    suspend fun fetchTestData1(id : Int) : BaseResult<TestModelDomain, Failure>
}