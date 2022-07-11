package com.ahinfo.ahteam.domain.authorization.useCase

import com.ahinfo.ahteam.domain.authorization.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend fun fetchAuthData() = repository.fetchAuthData()
}