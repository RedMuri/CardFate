package com.example.cardfate.domain.usecases.auth

import com.example.cardfate.domain.repository.AuthRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    operator fun invoke(email: String, password: String) =
        repository.logIn(email,password)
}