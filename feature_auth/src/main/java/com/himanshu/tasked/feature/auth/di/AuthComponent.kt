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
        AuthModule::class
    ],
    dependencies = [CoreComponent::class]
)
interface AuthComponent {

    fun inject(fragment: LoginFragment)

    fun inject(fragment: RegisterFragment)

    fun inject(fragment: ForgotPasswordFragment)

    fun inject(activity: AuthActivity)
}