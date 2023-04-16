package com.example.cardfate.di

import android.app.Application
import com.example.cardfate.presentation.activity.MainActivity
import com.example.cardfate.presentation.fragment.CreateCardFragment
import com.example.cardfate.presentation.fragment.LogInFragment
import com.example.cardfate.presentation.fragment.MainFragment
import com.example.cardfate.presentation.fragment.SignInFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(signInFragment: SignInFragment)
    fun inject(logInFragment: LogInFragment)
    fun inject(createCardFragment: CreateCardFragment)
    fun inject(mainFragment: MainFragment)
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application,
        ): ApplicationComponent
    }
}