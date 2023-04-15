package com.example.cardfate.domain.repository

interface UsersRepository {

    suspend fun signIn(login: String, password: String, callback: (String) -> Unit)

    suspend fun logIn(login: String, password: String, callback: (String) -> Unit)
}