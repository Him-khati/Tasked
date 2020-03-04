package com.himanshu.tasked.feature.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.himanshu.tasked.core.TaskState
import com.himanshu.tasked.core.base.BaseFragment
import com.himanshu.tasked.core.base.CoreApplication
import com.himanshu.tasked.feature.auth.R
import com.himanshu.tasked.feature.auth.databinding.FragmentLoginBinding
import com.himanshu.tasked.feature.auth.login.di.DaggerLoginComponent
import com.himanshu.tasked.feature.auth.login.di.LoginModule

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    companion object {
        const val RC_SIGN_IN = 102
    }

    override fun onInitDependencyInjection() {
        val coreComponent = (requireCompatActivity().application as CoreApplication)
            .initOrGetCoreDependency()

        DaggerLoginComponent
            .builder()
            .coreComponent(coreComponent)
            .loginModule(
                LoginModule(
                    this
                )
            )
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {
        viewBinding.googleLoginBtn.setOnClickListener {
            viewModel.loginWithGoogle(requireContext())
        }

        viewBinding.loginBtn.setOnClickListener {
            viewModel.login(
                viewBinding.userNameET.text.toString(),
                viewBinding.passwordET.text.toString()
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniViewModel()
    }

    private fun iniViewModel() {

        viewModel.loginFormState.observe(
            viewLifecycleOwner,
            Observer {
                val loginState = it ?: return@Observer

                if (loginState.usernameError != null) {
                    viewBinding.userNameInputLayout.error = getString(loginState.usernameError)
                }
                if (loginState.passwordError != null) {
                    viewBinding.passwordInputLayout.error = getString(loginState.passwordError)
                }
            })

        viewModel.loginResult.observe(
            viewLifecycleOwner,
            Observer {
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

        viewModel.startGoogleSignInActivity.observe(
            viewLifecycleOwner,
            Observer { signInIntent ->
                startActivityForResult(signInIntent, RC_SIGN_IN)
            })
    }

    private fun showLoginFailed(errorString: String) {
        Toast.makeText(requireContext(), errorString, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            viewModel.handleGoogleSignInResult(data)
        }
    }

}


