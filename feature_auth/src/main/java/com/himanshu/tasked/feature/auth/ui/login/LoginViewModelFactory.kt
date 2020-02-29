package com.himanshu.tasked.feature.auth.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.himanshu.tasked.core.analytics.AnalyticsHelperImpl
import com.himanshu.tasked.core.logger.LoggerImpl
import com.himanshu.tasked.data.sessionManagement.UserSessionManager

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory constructor(private val context : Context): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {

            val firebaseApp = FirebaseApp.getInstance()
            return LoginViewModel(
                userSessionManager = UserSessionManager(FirebaseAuth.getInstance(firebaseApp)),
                logger = LoggerImpl(),
                analytics = AnalyticsHelperImpl(FirebaseAnalytics.getInstance(context))
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}