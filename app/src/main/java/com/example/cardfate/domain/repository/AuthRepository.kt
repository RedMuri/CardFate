package com.example.cardfate.domain.repository

interface AuthRepository {

    fun signIn(login: String, password: String)
    fun logIn(login: String, password: String)
}