package com.example.cardfate.domain.repository

import com.example.homehelper.domain.entities.User

interface UsersRepository {

    suspend fun signIn(user: User, callback: () -> Unit)

    suspend fun logIn(phone: String, password: String, callback: () -> Unit)
}