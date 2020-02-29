package com.himanshu.tasked.feature.auth.ui

import com.himanshu.tasked.core.base.BaseActivity
import com.himanshu.tasked.core.base.CoreApplication
import com.himanshu.tasked.feature.auth.R
import com.himanshu.tasked.feature.auth.databinding.ActivityAuthBinding
import com.himanshu.tasked.feature.auth.di.DaggerAuthComponent


class AuthActivity : BaseActivity<ActivityAuthBinding>(R.layout.activity_auth) {

    override fun onInitDependencyInjection() {

        val coreComponent = (application as CoreApplication).initOrGetCoreDependency()
        DaggerAuthComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
    }

    override fun onInitDataBinding() {
        viewBinding.lifecycleOwner = this
    }
}
