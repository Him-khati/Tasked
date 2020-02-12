package com.himanshu.tasked.feature.auth.ui.login

import android.content.Intent
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.himanshu.tasked.androidbase.TaskState
import com.himanshu.tasked.data.sessionManagement.UserSessionManager
import com.himanshu.tasked.feature.auth.R
import kotlinx.coroutines.launch

class LoginViewModel(private val userSessionManager: UserSessionManager) : ViewModel() {

    companion object {
        private const val WEB_CLIENT_ID = "X"
    }

    private lateinit var googleSignInClient: GoogleSignInClient

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<TaskState<String>>()
    val loginResult: LiveData<TaskState<String>> = _loginResult

    private val _startGoogleSignInActivity = MutableLiveData<Intent>()
    val startGoogleSignInActivity: LiveData<Intent> = _startGoogleSignInActivity

    fun loginWithGoogle(loginActivity: LoginActivity) {

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(WEB_CLIENT_ID)
            .requestEmail()
            .requestProfile()
            .build()

        googleSignInClient = GoogleSignIn.getClient(loginActivity, googleSignInOptions)
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

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
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

        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            // Google Sign In was successful, authenticate with Firebase
            val account = task.getResult(ApiException::class.java)
            // userSessionManager.loginWithGoogle(account!!)
            //firebaseAuthWithGoogle(account!!)
        } catch (e: ApiException) {
            // Google Sign In failed, update UI appropriately
            // ...
        }
    }
}