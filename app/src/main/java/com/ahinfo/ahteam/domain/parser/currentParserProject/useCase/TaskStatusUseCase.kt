package com.ahinfo.ahteam.domain.parser.currentParserProject.useCase

import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.RequestGetParserTaskStatus
import com.ahinfo.ahteam.domain.parser.currentParserProject.repository.ParserTaskRepository
import javax.inject.Inject

class TaskStatusUseCase @Inject constructor(
    private val repository : ParserTaskRepository
){
    suspend fun getParserTaskStatus(request: RequestGetParserTaskStatus) =
        repository.getParserTaskStatus(request)

    fun loadCurrentProjectId() = repository.loadCurrentProjectId()

    fun saveCurrentProjectId(value : Int) { repository.saveCurrentProjectId(value) }

    fun loadCurrentTaskId() = repository.loadCurrentTaskId()

    fun saveCurrentTaskId(value : Int) { repository.saveCurrentTaskId(value) }

    fun loadPage() = repository.loadPage()

    fun savePage(value : Int) { repository.savePage(value) }

    fun loadCountProjectsOnPage() = repository.loadCountProjectsOnPage()

    fun saveCountProjectsOnPage(value : Int) { repository.saveCountProjectsOnPage(value) }

    fun returnToDefaultSettings() { repository.returnToDefaultSettings() }
}