package com.example.cardfate.domain.usecases.auth

import com.example.cardfate.domain.repository.UsersRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(name: String, password: String, callback: (String) -> Unit) =
        usersRepository.logIn(name, password, callback)
}