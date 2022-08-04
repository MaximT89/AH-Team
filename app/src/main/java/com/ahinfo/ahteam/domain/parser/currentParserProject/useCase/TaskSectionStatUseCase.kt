package com.ahinfo.ahteam.domain.parser.currentParserProject.useCase

import com.ahinfo.ahteam.domain.parser.currentParserProject.repository.ParserTaskRepository
import javax.inject.Inject

class TaskSectionStatUseCase @Inject constructor(
    private val repository: ParserTaskRepository
) {
    suspend fun getSectionStat(taskId: Int) = repository.getSectionStat(taskId)
}