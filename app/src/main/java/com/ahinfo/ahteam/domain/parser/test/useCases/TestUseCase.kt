package com.ahinfo.ahteam.domain.parser.test.useCases

import com.ahinfo.ahteam.domain.parser.test.repository.TestRepository
import javax.inject.Inject

class TestUseCase @Inject constructor(private val repository: TestRepository){

    suspend fun fetchTestData1(id : Int) = repository.fetchTestData1(id)
}