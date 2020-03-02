package com.himanshu.tasked.ui.launcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.himanshu.tasked.feature.auth.ui.login.LoginFragment

class LauncherActivity : AppCompatActivity() {

    private lateinit var launcherViewModel: LauncherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        launcherViewModel
            .checkLoginState
            .observe(this, Observer {

                when (it) {
                    LoginResult.LOGGED_IN -> startMainActivity()
                    LoginResult.NOT_LOGGED_IN -> startLoginActivity()
                }
            })
    }

    private fun startLoginActivity() {
        startActivity(Intent(this, LoginFragment::class.java))
        finish()
    }

    private fun startMainActivity() {

    }
}
