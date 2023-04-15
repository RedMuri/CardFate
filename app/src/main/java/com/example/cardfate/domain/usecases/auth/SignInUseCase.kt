package com.example.cardfate.domain.usecases.auth

import com.example.cardfate.domain.repository.UsersRepository
import com.example.homehelper.domain.entities.User
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(user: User, callback: () -> Unit) =
        usersRepository.signIn(user, callback)
}