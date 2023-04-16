package com.example.cardfate.domain.repository

import com.example.cardfate.domain.entity.Card

interface CardRepository {

    suspend fun uploadCard(card: Card, callback: () -> Unit)

    suspend fun getCardsByUserId(userId: String, callback: (List<Card>) -> Unit)
}