package com.himanshu.tasked.feature.auth.di

import androidx.lifecycle.ViewModel
import com.himanshu.tasked.core.di.modules.ViewModelKey
import com.himanshu.tasked.feature.auth.ui.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindProfileViewModel(viewModel: LoginViewModel): ViewModel

}