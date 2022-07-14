package com.ahinfo.ahteam.data.parser.test

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.test.remote.TestDataCloudSource
import com.ahinfo.ahteam.domain.parser.test.entity.TestModelDomain
import com.ahinfo.ahteam.domain.parser.test.repository.TestRepository
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(private val cloudDataSource: TestDataCloudSource) :
    TestRepository {

    override suspend fun fetchTestData1(id : Int): BaseResult<TestModelDomain, Failure> =
        cloudDataSource.getResultTestData(id)

}