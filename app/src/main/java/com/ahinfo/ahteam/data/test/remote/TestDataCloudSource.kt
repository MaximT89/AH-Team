package com.ahinfo.ahteam.data.test.remote

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.core.remote.ResponseWrapper
import com.ahinfo.ahteam.data.test.remote.api.TestService
import com.ahinfo.ahteam.domain.test.entity.TestModelDomain
import javax.inject.Inject

class TestDataCloudSource @Inject constructor(
    private val mapper: TestDataToDomainMapper,
    private val api: TestService,
    private val responseWrapper: ResponseWrapper
)  {

    suspend fun getResultTestData() : BaseResult<TestModelDomain, Failure> =
        responseWrapper.handleResponse(mapper){
            api.getTestDataFromServer()
        }
}