package com.himanshu.tasked.feature.auth.ui.login

import android.content.Context
import android.content.Intent
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.himanshu.tasked.core.TaskState
import com.himanshu.tasked.core.analytics.AnalyticsHelper
import com.himanshu.tasked.core.di.scopes.FeatureScope
import com.himanshu.tasked.data.sessionManagement.UserSessionManager
import com.himanshu.tasked.feature.auth.R
import com.himanshu.tasked.feature.auth.analytics.AuthAnalytics
import com.himanshu.tasked.feature.auth.ui.AuthViewModel
import com.vinners.core.logger.Logger
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val userSessionManager: UserSessionManager,
    private val logger: Logger,
    private val analytics: AnalyticsHelper
) : AuthViewModel(userSessionManager) {

    companion object {
        private const val WEB_CLIENT_ID =
            "1048743591386-a7o4olc8ucgtorf785jghjm90o6goklt.apps.googleusercontent.com"
    }

    private lateinit var googleSignInClient: GoogleSignInClient

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<TaskState<String>>()
    val loginResult: LiveData<TaskState<String>> = _loginResult

    private val _startGoogleSignInActivity = MutableLiveData<Intent>()
    val startGoogleSignInActivity: LiveData<Intent> = _startGoogleSignInActivity

    fun loginWithGoogle(context: Context) {

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(WEB_CLIENT_ID)
            .requestEmail()
            .requestProfile()
            .build()

        googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)
        _startGoogleSignInActivity.value = googleSignInClient.signInIntent
    }

    fun login(email: String, password: String) {

        if (!isUserNameValid(email)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
            return
        }

        if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
            return
        }

        _loginResult.value = TaskState.Loading
        viewModelScope.launch {
            try {

                userSessionManager.loginWithEmailAndPassword(email, password)
                _loginResult.postValue(TaskState.Success("Login Successful"))
            } catch (e: Exception) {
                _loginResult.postValue(TaskState.Error(e.message!!))
            }
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(username).matches()
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    fun handleGoogleSignInResult(data: Intent?) {

        viewModelScope.launch {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                userSessionManager.loginWithGoogle(account!!)

                // Logging Event In Analytics
                logInAnalytics()
                _loginResult.postValue(TaskState.Success("Login Successful"))
            } catch (e: ApiException) {
                // Google Sign In failed
                logger.e(e, "Google Login Failed")
                _loginResult.postValue(TaskState.Error(e.localizedMessage))
            } catch (e: Exception) {
                // Some OOther Error
                logger.e(e, "Error While Registering Google User With Firebase")
                _loginResult.postValue(TaskState.Error(e.localizedMessage))
            }
        }
    }

    private fun logInAnalytics() {
        try {
            analytics.logEvent(AuthAnalytics.Events.LOGIN_GOOGLE_SUCCESS, null)
        } catch (e: Exception) {
            logger.e(e, "Unable to Log Event In Analytics")
        }
    }
}