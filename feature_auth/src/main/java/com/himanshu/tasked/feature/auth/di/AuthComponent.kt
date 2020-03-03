package com.himanshu.tasked.feature.auth.di

import com.himanshu.tasked.core.di.CoreComponent
import com.himanshu.tasked.core.di.scopes.FeatureScope
import com.himanshu.tasked.feature.auth.ui.AuthActivity
import com.himanshu.tasked.feature.auth.ui.forgotPassword.ForgotPasswordFragment
import com.himanshu.tasked.feature.auth.ui.login.LoginFragment
import com.himanshu.tasked.feature.auth.ui.register.RegisterFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [
        LoginModule::class,
        ForgotPasswordModule::class
    ],
    dependencies = [CoreComponent::class]
)
interface AuthComponent {

    fun inject(authActivity: AuthActivity)

    fun inject(loginFragment: LoginFragment)

    fun inject(forgotPasswordFragment: ForgotPasswordFragment)

    fun inject(registerFragment: RegisterFragment)
}