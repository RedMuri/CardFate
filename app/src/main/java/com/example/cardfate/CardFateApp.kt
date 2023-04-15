package com.example.cardfate

import android.app.Application

class CardFateApp: Application() {

    val component by lazy {
        DaggerApplicationComponent
            .factory()
            .create(this)
    }
}