package com.himanshu.tasked.feature.auth.di

import androidx.lifecycle.ViewModel
import com.himanshu.tasked.core.di.modules.ViewModelKey
import com.himanshu.tasked.feature.auth.ui.forgotPassword.ForgotPasswordViewModel
import com.himanshu.tasked.feature.auth.ui.login.LoginViewModel
import com.himanshu.tasked.feature.auth.ui.register.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(viewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForgotPasswordViewModel::class)
    abstract fun bindForgotPasswordViewModel(viewModel: ForgotPasswordViewModel): ViewModel



}