package com.ahinfo.ahteam.domain.parser.listProjects.useCases

import com.ahinfo.ahteam.domain.parser.listProjects.repositoty.ListProjectsRepository
import javax.inject.Inject

class ListProjectUseCase @Inject constructor(private val repository: ListProjectsRepository) {

    suspend fun getListProjects(
        pageNumber: Int,
        countElementOnPage: Int
    ) = repository.getListProjects(
        pageNumber,
        countElementOnPage
    )
}