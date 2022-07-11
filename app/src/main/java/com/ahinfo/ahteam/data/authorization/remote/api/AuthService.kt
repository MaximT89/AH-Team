package com.ahinfo.ahteam.data.authorization.remote.api

import com.ahinfo.ahteam.data.authorization.remote.dto.ResponseTodo
import retrofit2.Response
import retrofit2.http.GET

interface AuthService {

    /**
     * Запрос на авторизацию пользователя в системе
     */
    @GET("/todo/9872")
    suspend fun signIn() : Response<ResponseTodo>
}