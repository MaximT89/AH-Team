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

    fun loadPage(): Int = repository.loadPage()

    fun savePage(value: Int) {
        repository.savePage(value)
    }

    fun loadCountProjectsOnPage(): Int = repository.loadCountProjectsOnPage()

    fun saveCountProjectsOnPage(value: Int) {
        repository.saveCountProjectsOnPage(value)
    }

    fun returnToDefaultSettings() {
        repository.returnToDefaultSettings()
    }
}