package com.ahinfo.ahteam.domain.parser.currentParserProject.useCase

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.RequestGetParserTaskStatus
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetParserTaskStatusDomain
import com.ahinfo.ahteam.domain.parser.currentParserProject.repository.GetParserTaskStatusRepository
import javax.inject.Inject

class GetParserTaskStatusUseCase @Inject constructor(
    private val repository : GetParserTaskStatusRepository
){

    suspend fun getParserTaskStatus(
        request: RequestGetParserTaskStatus
    ) : BaseResult<GetParserTaskStatusDomain, Failure>{
        return repository.getParserTaskStatus(request)
    }

    fun loadCurrentProjectId(): Int = repository.loadCurrentProjectId()

    fun saveCurrentProjectId(value : Int) {
        repository.saveCurrentProjectId(value)
    }

    fun loadCurrentTaskId(): Int = repository.loadCurrentTaskId()

    fun saveCurrentTaskId(value : Int){
        repository.saveCurrentTaskId(value)
    }

    fun loadPage() : Int = repository.loadPage()

    fun savePage(value : Int){
        repository.savePage(value)
    }

    fun loadCountProjectsOnPage() : Int = repository.loadCountProjectsOnPage()

    fun saveCountProjectsOnPage(value : Int) {
        repository.saveCountProjectsOnPage(value)
    }

    fun returnToDefaultSettings(){
        repository.returnToDefaultSettings()
    }

}