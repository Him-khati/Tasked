package com.himanshu.tasked.ui.launcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.himanshu.tasked.core.base.CoreApplication
import com.himanshu.tasked.feature.auth.ui.AuthActivity
import com.himanshu.tasked.ui.di.DaggerLauncherComponent
import com.himanshu.tasked.ui.di.LauncherModule
import javax.inject.Inject

class LauncherActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: LauncherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInitDependencyInjection()
        onInitViewModel()
    }

    private fun onInitDependencyInjection() {
        val coreComponent = (application as CoreApplication).initOrGetCoreDependency()
        DaggerLauncherComponent
            .builder()
            .coreComponent(coreComponent)
            .launcherModule(LauncherModule(this))
            .build()
            .inject(this)
    }

    private fun onInitViewModel() {
        viewModel.checkLoginState
            .observe(this, Observer {
                when (it) {
                    LoginResult.LOGGED_IN -> startMainActivity()
                    LoginResult.NOT_LOGGED_IN -> startLoginActivity()
                }
            })

        viewModel.checkPreviousLogin()
    }

    private fun startMainActivity() {

    }

    private fun startLoginActivity() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}
