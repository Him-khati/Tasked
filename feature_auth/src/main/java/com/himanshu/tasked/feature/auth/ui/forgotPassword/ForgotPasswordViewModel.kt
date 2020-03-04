package com.himanshu.tasked.feature.auth.ui.forgotPassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshu.tasked.core.TaskState
import com.himanshu.tasked.core.analytics.AnalyticsHelper
import com.himanshu.tasked.data.sessionManagement.UserSessionManager
import com.vinners.core.logger.Logger
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForgotPasswordViewModel @Inject constructor(
    private val userSessionManager: UserSessionManager,
    private val logger: Logger,
    private val analytics: AnalyticsHelper
) : ViewModel() {

    private val _sendResetLinkTaskState = MutableLiveData<TaskState<String>>()
    val sendResetLinkTaskState: LiveData<TaskState<String>> = _sendResetLinkTaskState

    fun sendResetLink(email: String) {

        _sendResetLinkTaskState.value = TaskState.Loading
        viewModelScope.launch {
            try {
                userSessionManager.sendPasswordResetLink(email)
                _sendResetLinkTaskState.postValue(TaskState.Success("Password Reset Link Sent"))
            } catch (e: Exception) {
                _sendResetLinkTaskState.postValue(
                    TaskState.Error(
                        e.message ?: "Unable to Send Password Reset Link"
                    )
                )
            }
        }
    }
}
