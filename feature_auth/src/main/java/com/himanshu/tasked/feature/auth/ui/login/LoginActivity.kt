package com.himanshu.tasked.feature.auth.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.himanshu.tasked.androidbase.TaskState
import com.himanshu.tasked.feature.auth.R

class LoginActivity : AppCompatActivity() {

    companion object {
        const val RC_SIGN_IN = 102
    }

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.dark)
        val password = findViewById<EditText>(R.id.dark)
        val login = findViewById<Button>(R.id.dark)
        val loading = findViewById<ProgressBar>(R.id.dark)


        loginViewModel =
            ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

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

        login.setOnClickListener {
            loginViewModel.login(
                username.text.toString()
                , password.text.toString()
            )
        }
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


