package com.ahinfo.ahteam.domain.parser.currentParserProject.repository

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.remote.Failure
import com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto.RequestGetParserTaskStatus
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetElementStatDomain
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetParserTaskStatusDomain
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetSectionStatDomain

interface ParserTaskRepository {

    suspend fun getParserTaskStatus(
        request: RequestGetParserTaskStatus
    ) : BaseResult<GetParserTaskStatusDomain, Failure>

    suspend fun getSectionStat(
        taskId : Int
    ) : BaseResult<GetSectionStatDomain, Failure>

    suspend fun getElementStat(
        taskId : Int
    ) : BaseResult<GetElementStatDomain, Failure>

    fun loadCurrentProjectId(): Int

    fun saveCurrentProjectId(value : Int)

    fun loadCurrentTaskId(): Int

    fun saveCurrentTaskId(value : Int)

    fun loadPage() : Int

    fun savePage(value : Int)

    fun loadCountProjectsOnPage() : Int

    fun saveCountProjectsOnPage(value : Int)

    fun returnToDefaultSettings()
}