package com.example.cardfate.di

import androidx.lifecycle.ViewModel
import com.example.cardfate.presentation.viewmodel.CreateCardViewModel
import com.example.cardfate.presentation.viewmodel.LogInViewModel
import com.example.cardfate.presentation.viewmodel.SignInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {


    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    @Binds
    fun bindSignInViewModel(viewModel: SignInViewModel): ViewModel

    @IntoMap
    @ViewModelKey(LogInViewModel::class)
    @Binds
    fun bindLogInViewModel(viewModel: LogInViewModel): ViewModel

    @IntoMap
    @ViewModelKey(CreateCardViewModel::class)
    @Binds
    fun bindCreateCardViewModel(viewModel: CreateCardViewModel): ViewModel
}