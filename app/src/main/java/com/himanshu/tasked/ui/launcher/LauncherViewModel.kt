package com.himanshu.tasked.ui.launcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.himanshu.tasked.data.sessionManagement.UserSessionManager
import com.himanshu.tasked.feature.auth.ui.AuthViewModel
import kotlinx.coroutines.launch

class LauncherViewModel(userSessionManager: UserSessionManager) :
    AuthViewModel(userSessionManager) {

    private val _checkLoginState = MutableLiveData<LoginResult>()
    val checkLoginState: LiveData<LoginResult> = _checkLoginState

    fun checkPreviousLogin() {

        viewModelScope.launch {
            if (isUserLoggedIn())
                _checkLoginState.postValue(LoginResult.LOGGED_IN)
            else
                _checkLoginState.postValue(LoginResult.NOT_LOGGED_IN)
        }
    }
}