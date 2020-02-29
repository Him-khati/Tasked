package com.himanshu.tasked.feature.auth.ui

import androidx.lifecycle.ViewModel
import com.himanshu.tasked.data.models.LoggedInUser
import com.himanshu.tasked.data.sessionManagement.UserSessionManager

open class AuthViewModel(
    private val userSessionManager: UserSessionManager
) : ViewModel() {

    suspend fun isUserLoggedIn(): Boolean {
        return userSessionManager.getLoggedInUser() != null
    }

    @Throws(IllegalStateException::class)
    suspend fun getLoggedInUser(): LoggedInUser {
        return userSessionManager.getLoggedInUser()
            ?: throw IllegalStateException("getLoggedInUser() , no logged In User")
    }
}