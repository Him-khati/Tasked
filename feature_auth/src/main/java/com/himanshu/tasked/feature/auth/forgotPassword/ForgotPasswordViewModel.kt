package com.himanshu.tasked.feature.auth.forgotPassword

import androidx.lifecycle.ViewModel
import com.himanshu.tasked.core.analytics.AnalyticsHelper
import com.himanshu.tasked.data.sessionManagement.UserSessionManager
import com.vinners.core.logger.Logger
import javax.inject.Inject

class ForgotPasswordViewModel @Inject constructor(
    private val userSessionManager: UserSessionManager,
    private val logger: Logger,
    private val analytics: AnalyticsHelper

): ViewModel() {

    fun doS(){

    }
}
