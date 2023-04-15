package com.example.cardfate.di

import android.app.Application
import com.example.cardfate.presentation.activity.MainActivity
import com.example.cardfate.presentation.fragment.LogInFragment
import com.example.cardfate.presentation.fragment.SignInFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(signInFragment: SignInFragment)
    fun inject(logInFragment: LogInFragment)
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application,
        ): ApplicationComponent
    }
}