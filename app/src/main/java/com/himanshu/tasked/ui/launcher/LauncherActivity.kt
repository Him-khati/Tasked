package com.himanshu.tasked.ui.launcher

import android.content.Intent
import androidx.lifecycle.Observer
import com.himanshu.tasked.R
import com.himanshu.tasked.core.base.BaseActivityWViewModel
import com.himanshu.tasked.core.base.CoreApplication
import com.himanshu.tasked.databinding.ActivityLauncherBinding
import com.himanshu.tasked.feature.auth.ui.AuthActivity
import com.himanshu.tasked.ui.di.DaggerLauncherComponent
import com.himanshu.tasked.ui.di.LauncherModule

class LauncherActivity :
    BaseActivityWViewModel<ActivityLauncherBinding, LauncherViewModel>(R.layout.activity_launcher) {

    override fun onInitDependencyInjection() {
        val coreComponent = (application as CoreApplication).initOrGetCoreDependency()
        DaggerLauncherComponent
            .builder()
            .coreComponent(coreComponent)
            .launcherModule(LauncherModule(this))
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {
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
