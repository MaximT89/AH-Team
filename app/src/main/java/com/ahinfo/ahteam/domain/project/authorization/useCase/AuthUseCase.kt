package com.ahinfo.ahteam.domain.project.authorization.useCase

import com.ahinfo.ahteam.domain.project.authorization.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend fun fetchAuthData() = repository.fetchAuthData()
}