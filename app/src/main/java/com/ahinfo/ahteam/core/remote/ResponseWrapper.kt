package com.ahinfo.ahteam.core.remote

import com.ahinfo.ahteam.core.bases.BaseResult
import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.core.extension.log
import retrofit2.Response
import java.net.ProtocolException
import javax.inject.Inject

/**
 * Класс для приема запроса с сервера и разбора его, на выходе мы получаем [BaseResult] в который
 * передаем тип данных соответствующий запросу или ошибку которую получаем в ходе разбора запроса
 */
interface ResponseWrapper {

    suspend fun <T, R> handleResponse(
        mapper: Mapper<T, R>,
        apiRequest: suspend () -> Response<T>
    ): BaseResult<R, Failure>

    class Base @Inject constructor() : ResponseWrapper {

        override suspend fun <T, R> handleResponse(
            mapper: Mapper<T, R>,
            apiRequest: suspend () -> Response<T>
        ): BaseResult<R, Failure> {

            log("-- handleResponse check --")

            return try {
                val response = apiRequest.invoke()

                if (response.isSuccessful) {
                    val body = response.body()
                    log("success body = $body")
                    BaseResult.Success(mapper.map(body!!))
                } else {
                    log("error body = ${response.message()}")
                    BaseResult.Error(Failure(response.code(), response.message()))
                }
            } catch (e: NoInternetConnectionException) {
                log("NoInternetConnectionException")
                BaseResult.Error(Failure(0, e.message))
            } catch (e: ProtocolException) {
                log("!!!!! Protocol exception !!!!!")
                BaseResult.Error(Failure(1, "Protocol exception"))
            }
             catch (e: Exception) {
                e.printStackTrace()
                log("Exception ${e.message}")
                log("Exception $e")
                log("Exception ${e.printStackTrace()}")
                BaseResult.Error(Failure(-1, e.message.toString()))
            }
        }
    }
}