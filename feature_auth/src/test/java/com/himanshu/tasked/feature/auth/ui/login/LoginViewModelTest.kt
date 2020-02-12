package com.himanshu.tasked.feature.auth.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.himanshu.tasked.androidbase.TaskState
import com.himanshu.tasked.data.models.LoggedInUser
import com.himanshu.tasked.data.sessionManagement.UserSessionManager
import com.himanshu.tasked.feature.auth.R
import com.himanshu.tasked.feature.auth.ui.CoroutinesTestRule
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class LoginViewModelTest {

    companion object {
        private const val INVALID_EMAIL = "invalidemail.com"
        private const val INVALID_PASSWORD = "" //Empty String

        private const val VALID_EMAIL = "valid@email.com"
        private const val VALID_NON_EXISTENT_EMAIL = "validNonExistent@email.com"
        private const val VALID_PASSWORD = "ValidPassword"

        private val LOGGED_IN_USER = LoggedInUser(
            sessionToken = "cjnsuec",
            displayName = "Harami",
            email = "Ddd"
        )
    }

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()


    //Mocks
    @Mock
    lateinit var userSessionManager: UserSessionManager

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.viewModel = LoginViewModel(
            this.userSessionManager
        )
    }

    @Test
    fun test_invalid_email() {
        viewModel.login(
            email = INVALID_EMAIL,
            password = VALID_PASSWORD
        )

        assert(viewModel.loginFormState.value!!.usernameError == R.string.invalid_username)
    }

    @Test
    fun test_invalid_password() {
        viewModel.login(
            email = VALID_EMAIL,
            password = INVALID_PASSWORD
        )

        assert(viewModel.loginFormState.value!!.passwordError == R.string.invalid_password)
    }

    @Test
    fun test_loginWithEmailAndPasswordSuccess() = runBlocking {

        whenever(
            userSessionManager.loginWithEmailAndPassword(
                email = VALID_EMAIL,
                password = VALID_PASSWORD
            )
        ).thenReturn(LOGGED_IN_USER)

        viewModel.login(
            email = VALID_EMAIL,
            password = VALID_PASSWORD
        )

        // No validation Error
        assert(viewModel.loginFormState.value == null)
        print(viewModel.loginResult.value)
 //       assert(viewModel.loginResult.value == TaskState.Loading)

        verify(userSessionManager, times(1))
            .loginWithEmailAndPassword(
                email = VALID_EMAIL,
                password = VALID_PASSWORD
            )

        assert(viewModel.loginResult.value is TaskState<String>)
        assert((viewModel.loginResult.value as TaskState.Success<String>).item.equals("Login Successful"))
    }

    @Test
    fun test_loginWithEmailAndPasswordFailure() = runBlocking {

        whenever(
            userSessionManager.loginWithEmailAndPassword(
                email = VALID_NON_EXISTENT_EMAIL,
                password = VALID_PASSWORD
            )
        ).doThrow(IllegalArgumentException("Invalid Email"))

        viewModel.login(
            email = VALID_NON_EXISTENT_EMAIL,
            password = VALID_PASSWORD
        )

        assert(viewModel.loginFormState.value == null)
//        assert(viewModel.loginResult.value == TaskState.Loading)

        verify(userSessionManager, times(1))
            .loginWithEmailAndPassword(
                email = VALID_NON_EXISTENT_EMAIL,
                password = VALID_PASSWORD
            )

        assert(viewModel.loginResult.value is TaskState.Error)
        assert((viewModel.loginResult.value as TaskState.Error).error.equals("Invalid Email"))
    }


}
