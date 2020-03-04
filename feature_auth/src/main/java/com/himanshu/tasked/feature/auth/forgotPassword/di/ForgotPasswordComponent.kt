package com.himanshu.tasked.feature.auth.forgotPassword.di

import com.himanshu.tasked.core.di.CoreComponent
import com.himanshu.tasked.core.di.scopes.FeatureScope
import com.himanshu.tasked.feature.auth.forgotPassword.ForgotPasswordFragment
import com.himanshu.tasked.feature.auth.login.LoginFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [
        ForgotPasswordModule::class
    ],
    dependencies = [CoreComponent::class]
)
interface ForgotPasswordComponent {

    fun inject(fragment: ForgotPasswordFragment)
}