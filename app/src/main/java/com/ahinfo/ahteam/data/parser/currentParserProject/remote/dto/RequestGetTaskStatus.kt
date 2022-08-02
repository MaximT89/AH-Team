package com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Модель для запроса всех задач для [projectId]
 *
 * В данном случае мы используем эту модель для получения статус конкретной задачи по парсингу,
 * поэтому обязательно нужно передавать [projectId] и [parsingId]
 *
 * @param projectId указаваем задачи какого проекта будем выводить
 * @param page указываем страницу (в данном запросе параметр скорее будет неизменным)
 * @param countItems указываем количество изделий на странице
 * @param parsingId если передаем null , то запрос вернет все задачи по проекту, если укажем конкретный
 * id, то получим данные по задаче конкретного проекта
 *
 */
data class RequestGetTaskStatus(
    @SerializedName("project_id")
    val projectId: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("count_items")
    val countItems: Int,
    @SerializedName("parsing_id")
    val parsingId: Int?
)