package com.himanshu.tasked.feature.auth

import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.google.firebase.FirebaseApp
import com.himanshu.tasked.feature.auth.ui.login.LoginActivity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityInstrumentedTest {

    companion object {

        private const val VALID_EMAIL = "valid.email@somemail.com"
        private const val INVALID_EMAIL = "valid.email"
    }

    @get:Rule
    var activityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        FirebaseApp.initializeApp(appContext)
    }

    private fun getResourceString(@StringRes id : Int) : String{
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        return appContext.getString(id)
    }

    @Test
    fun testInvalidEmailCheck() {
        onView(withId(R.id.userNameET))
            .perform(typeText(INVALID_EMAIL), closeSoftKeyboard())

        onView(withId(R.id.loginBtn))
            .perform(click())

        onView(withId(R.id.userNameET))
            .check(matches(hasErrorText(getResourceString(R.string.invalid_username))))
    }
}
