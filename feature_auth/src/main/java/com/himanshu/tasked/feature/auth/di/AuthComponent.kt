package com.himanshu.tasked.feature.auth.di

import com.himanshu.tasked.core.di.CoreComponent
import com.himanshu.tasked.core.di.scopes.FeatureScope
import com.himanshu.tasked.feature.auth.ui.AuthActivity
import com.himanshu.tasked.feature.auth.ui.forgotPassword.ForgotPasswordFragment
import com.himanshu.tasked.feature.auth.ui.login.LoginActivity
import dagger.Component

@FeatureScope
@Component(
    modules = [AuthModule::class]
    ,dependencies = [CoreComponent::class])
interface AuthComponent {

    /**
     * Inject dependencies on component.
     *
     * @param LoginActivity Home component.
     */
    fun inject(loginActivity : LoginActivity)

    fun inject(authActivity: AuthActivity)

    fun inject(forgotPasswordFragment : ForgotPasswordFragment)
}