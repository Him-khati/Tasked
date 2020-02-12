package com.himanshu.tasked.feature.auth.ui.login

sealed class LoginResult {

    /**
     * Loading The Data
     */
    object Loading : LoginResult()

    /**
     * Error While Loading Data
     */
    data class Error(val error: Int) : LoginResult()

    /**
     * Success ,Data Loaded or TaskDone
     */
    object Success : LoginResult()
}