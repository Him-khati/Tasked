package com.himanshu.tasked.feature.auth.di

import com.himanshu.tasked.core.analytics.AnalyticsHelper
import com.himanshu.tasked.core.di.scopes.FeatureScope
import com.himanshu.tasked.core.extensions.viewModel
import com.himanshu.tasked.data.sessionManagement.UserSessionManager
import com.himanshu.tasked.feature.auth.ui.forgotPassword.ForgotPasswordFragment
import com.himanshu.tasked.feature.auth.ui.forgotPassword.ForgotPasswordViewModel
import com.vinners.core.logger.Logger
import dagger.Module
import dagger.Provides

@Module
class ForgotPasswordModule(
    val fragment: ForgotPasswordFragment
) {

    @Provides
    @FeatureScope
    fun providesForgotPasswordViewModel(
        userSessionManager: UserSessionManager,
        logger: Logger,
        analytics: AnalyticsHelper
    ) = fragment.viewModel {
        ForgotPasswordViewModel(
            userSessionManager = userSessionManager,
            logger = logger,
            analytics = analytics
        )
    }
}