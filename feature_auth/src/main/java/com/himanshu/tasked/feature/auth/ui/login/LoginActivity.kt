package com.himanshu.tasked.feature.auth.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.analytics.FirebaseAnalytics
import com.himanshu.tasked.core.TaskState
import com.himanshu.tasked.feature.auth.R

class LoginActivity : AppCompatActivity() {

    companion object {
        const val RC_SIGN_IN = 102
    }

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var analytics: FirebaseAnalytics

    //Views
    private lateinit var username: EditText
    private lateinit var password: EditText

    private lateinit var login: View
    private lateinit var googleLoginBtn: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        iniViewModel()
    }

    private fun initView() {
        username = findViewById(R.id.userNameET)
        password = findViewById(R.id.passwordET)
        login = findViewById(R.id.loginBtn)
        googleLoginBtn = findViewById(R.id.googleLoginBtn)

        googleLoginBtn.setOnClickListener {
            loginViewModel.loginWithGoogle(this)
        }

        login.setOnClickListener {
            loginViewModel.login(
                username.text.toString()
                , password.text.toString()
            )
        }
    }

    private fun iniViewModel() {

        loginViewModel =
            ViewModelProvider(this, LoginViewModelFactory(this)).get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            when (loginResult) {
                is TaskState.Loading -> {
                }
                is TaskState.Success -> {

                }
                is TaskState.Error -> {
                    showLoginFailed(loginResult.error)
                }
            }
        })

        loginViewModel.startGoogleSignInActivity.observe(
            this@LoginActivity,
            Observer { signInIntent ->
                startActivityForResult(signInIntent, RC_SIGN_IN)
            })
    }

    private fun showLoginFailed(errorString: String) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            loginViewModel.handleGoogleSignInResult(data)
        }
    }

}


