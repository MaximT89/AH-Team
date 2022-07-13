package com.ahinfo.ahteam.domain.test.repository

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.domain.test.entity.TestModelDomain

interface TestRepository {

    suspend fun fetchTestData1() : BaseResult<TestModelDomain, Failure>
}