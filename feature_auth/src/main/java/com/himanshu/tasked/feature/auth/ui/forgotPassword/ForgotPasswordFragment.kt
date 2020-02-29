package com.himanshu.tasked.feature.auth.ui.forgotPassword

import com.himanshu.tasked.core.base.BaseFragment
import com.himanshu.tasked.core.base.CoreApplication
import com.himanshu.tasked.feature.auth.R
import com.himanshu.tasked.feature.auth.databinding.FragmentForgotPasswordBinding
import com.himanshu.tasked.feature.auth.di.DaggerAuthComponent

class ForgotPasswordFragment :
    BaseFragment<FragmentForgotPasswordBinding, ForgotPasswordViewModel>(R.layout.fragment_forgot_password) {

    override fun onInitDependencyInjection() {

        val coreComponent = (requireCompatActivity().application as CoreApplication)
            .initOrGetCoreDependency()
        DaggerAuthComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
    }

    override fun onInitDataBinding() {
        viewBinding.lifecycleOwner = viewLifecycleOwner
    }
}
