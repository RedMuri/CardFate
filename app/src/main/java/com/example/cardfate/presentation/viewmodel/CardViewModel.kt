package com.example.cardfate.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardfate.domain.entity.Card
import com.example.cardfate.domain.usecases.GetCardByIdUseCase
import com.example.cardfate.domain.usecases.GetCardsByUserIdUseCase
import com.example.cardfate.domain.usecases.LogInUseCase
import com.example.cardfate.domain.usecases.UploadCardUseCase
import com.example.cardfate.presentation.state.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class CardViewModel @Inject constructor(
    private val getCardById: GetCardByIdUseCase,
) : ViewModel() {

    private var _card = MutableLiveData<Card>()
    val card: LiveData<Card> = _card

    fun getCardById(cardId: String) {
        viewModelScope.launch {
            getCardById.invoke(cardId) {
                try {
                    _card.value = it
                } catch (_: Exception) {
                }
            }
        }
    }
}