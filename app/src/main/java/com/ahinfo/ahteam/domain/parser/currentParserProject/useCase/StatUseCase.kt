package com.ahinfo.ahteam.domain.parser.currentParserProject.useCase

import com.ahinfo.ahteam.domain.parser.currentParserProject.repository.ParserTaskRepository
import javax.inject.Inject

class StatUseCase @Inject constructor(
    private val repository: ParserTaskRepository
) {
    suspend fun getSectionStat(taskId: Int) = repository.getSectionStat(taskId)

    suspend fun getElementStat(taskId: Int) = repository.getElementStat(taskId)
}