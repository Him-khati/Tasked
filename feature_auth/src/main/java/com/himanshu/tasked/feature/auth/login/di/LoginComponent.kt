package com.himanshu.tasked.feature.auth.login.di

import com.himanshu.tasked.core.di.CoreComponent
import com.himanshu.tasked.core.di.scopes.FeatureScope
import com.himanshu.tasked.feature.auth.login.LoginFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [
        LoginModule::class
    ],
    dependencies = [CoreComponent::class]
)
interface LoginComponent {

    fun inject(fragment: LoginFragment)
}