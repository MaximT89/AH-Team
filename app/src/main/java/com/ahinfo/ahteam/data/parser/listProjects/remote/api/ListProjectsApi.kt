package com.ahinfo.ahteam.data.parser.listProjects.remote.api

import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ResponseListProjects
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ListProjectsApi {

    /**
     * Запрашиваем список проектов для парсинга
     * @pageNumber - данный параметр определяет какая страница
     * @countElementOnPage - данный параметр определяет сколько элементов должно быть на странице
     */
    @GET("/parsing/getprojects/{pageNumber}/{countElementOnPage}/")
    suspend fun getListProjects(
        @Path("pageNumber") pageNumber: Int,
        @Path("countElementOnPage") countElementOnPage: Int
    ) : Response<ResponseListProjects>

    // TODO: занести pageNumber и countElementOnPage в файл с prefs
    // TODO: сделать базовый файл для pref
}