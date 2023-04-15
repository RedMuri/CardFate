package com.example.cardfate.domain.repository

import com.example.cardfate.domain.entity.User

interface UsersRepository {

    suspend fun signIn(user: User, callback: () -> Unit)

    suspend fun logIn(phone: String, password: String, callback: () -> Unit)
}