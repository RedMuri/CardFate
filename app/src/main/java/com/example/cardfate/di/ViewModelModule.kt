package com.example.cardfate.di

import com.example.cardfate.presentation.viewmodel.LogInViewModel
import com.example.cardfate.presentation.viewmodel.SignInViewModel
import com.example.domain.use_cases.LogInUseCase
import com.example.domain.use_cases.SignInUseCase
import dagger.Module
import dagger.Provides

@Module
object ViewModelModule {


    @Provides
    fun provideSignInViewModel(signInUseCase: SignInUseCase): SignInViewModel {
        return SignInViewModel(signInUseCase)
    }

    @Provides
    fun provideLogInViewModel(logInUseCase: LogInUseCase): LogInViewModel {
        return LogInViewModel(logInUseCase)
    }
}