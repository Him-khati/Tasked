package com.himanshu.tasked.feature.auth.di

import com.himanshu.tasked.core.analytics.AnalyticsHelper
import com.himanshu.tasked.core.di.scopes.FeatureScope
import com.himanshu.tasked.data.sessionManagement.UserSessionManager
import com.himanshu.tasked.feature.auth.ui.login.LoginFragment
import com.himanshu.tasked.feature.auth.ui.login.LoginViewModel
import com.vinners.core.logger.Logger
import dagger.Module
import dagger.Provides

@Module
class LoginModule(
    val fragment: LoginFragment
) {

    @Provides
    @FeatureScope
    fun providesLoginViewModel(
        userSessionManager: UserSessionManager,
        logger: Logger,
        analytics: AnalyticsHelper
    ) = fragment.viewModel {
        LoginViewModel(
            userSessionManager = userSessionManager,
            logger = logger,
            analytics = analytics
        )
    }
}