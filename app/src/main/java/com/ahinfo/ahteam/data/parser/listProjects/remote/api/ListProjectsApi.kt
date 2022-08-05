package com.ahinfo.ahteam.data.parser.listProjects.remote.api

import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ResponseDeleteProject
import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ResponseListProjects
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ListProjectsApi {

    /**
     * Запрашиваем список проектов для парсинга
     * @pageNumber - данный параметр определяет какая страница
     * @countElementOnPage - данный параметр определяет сколько элементов должно быть на странице
     */
    @GET("/parsing/get.projects/{pageNumber}/{countElementOnPage}/")
    suspend fun getListProjects(
        @Path("pageNumber") pageNumber: Int,
        @Path("countElementOnPage") countElementOnPage: Int
    ) : Response<ResponseListProjects>

    /**
     * Данным запросом удаляем проект в [idProject] передаем id проекта, который собираемся удалять
     */
    @DELETE("/parsing/del.project/{idProject}/")
    suspend fun deleteProject(
        @Path("idProject") idProject: Int
    ) : Response<ResponseDeleteProject>


}