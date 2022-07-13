package com.ahinfo.ahteam.domain.test.useCase

import com.ahinfo.ahteam.domain.test.repository.TestRepository
import javax.inject.Inject

class TestUseCase @Inject constructor(private val repository: TestRepository){

    suspend fun fetchTestData1(id : Int) = repository.fetchTestData1(id)
}