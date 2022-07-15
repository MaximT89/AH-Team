package com.ahinfo.ahteam.domain.parser.listProjects.useCases

import com.ahinfo.ahteam.domain.parser.listProjects.repositoty.ListProjectsRepository
import javax.inject.Inject

class ListProjectsUseCase @Inject constructor(private val repository: ListProjectsRepository) {

    suspend fun getListProjects(
        pageNumber: Int,
        countElementOnPage: Int
    ) = repository.getListProjects(
        pageNumber,
        countElementOnPage
    )

    suspend fun deleteProject(idProject: Int) = repository.deleteProject(idProject)
}