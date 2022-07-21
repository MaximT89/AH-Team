package com.ahinfo.ahteam.data.parser.detailsProject.remote.api

import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ResponseGetProjectTasks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GetProjectTasksApi {

    /**
     * Данным запросом получаем все задачи по парсингу для проекта
     * @param projectId тут мы передаем id проекта, задачи которого хотим получить с сервера, данное
     * значение будет передаваться с предыдущего экрана, с экрана [ListProjectsFragment]
     * @param pageNumber номер страницы (на случай если задач будет очень много)
     * @param countItemOnPage сюда передаем количество итемов на странице
     */
    @GET("/parsing/parsing/get.parsing/{projectId}/{pageNumber}/{countItemOnPage}/")
    suspend fun getProjectTasks(
        @Path("projectId") projectId : Int,
        @Path("pageNumber") pageNumber : Int,
        @Path("countItemOnPage") countItemOnPage : Int,
    ) : Response<ResponseGetProjectTasks>
}