package com.ahinfo.ahteam.domain.parser.currentParserProject.useCase

import com.ahinfo.ahteam.domain.parser.currentParserProject.repository.ParserTaskRepository
import javax.inject.Inject

class ParserManageUseCase @Inject constructor(private val repository : ParserTaskRepository) {

    suspend fun parserStop(taskId : Int) = repository.parserStop(taskId)
}