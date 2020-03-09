package com.himanshu.tasked.feature.auth.ui.forgotPassword

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.himanshu.tasked.core.base.BaseFragment
import com.himanshu.tasked.core.base.CoreApplication
import com.himanshu.tasked.feature.auth.R
import com.himanshu.tasked.feature.auth.databinding.FragmentForgotPasswordBinding
import com.himanshu.tasked.feature.auth.di.AuthViewModelFactory
import com.himanshu.tasked.feature.auth.di.DaggerAuthComponent
import javax.inject.Inject

class ForgotPasswordFragment :
    BaseFragment<FragmentForgotPasswordBinding, ForgotPasswordViewModel>(R.layout.fragment_forgot_password) {

    @Inject
    lateinit var viewModelFactory: AuthViewModelFactory

    override fun onInitDependencyInjection() {
        val coreComponent = (requireCompatActivity().application as CoreApplication)
            .initOrGetCoreDependency()

        DaggerAuthComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {
        viewBinding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onInitViewModel() {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ForgotPasswordViewModel::class.java)

        viewModel
            .sendResetLinkTaskState
            .observe(this, Observer {

            })
    }
}
