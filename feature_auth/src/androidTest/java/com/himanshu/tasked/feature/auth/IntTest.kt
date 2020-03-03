package com.himanshu.tasked.feature.auth

import androidx.test.rule.ActivityTestRule
import com.himanshu.tasked.feature.auth.ui.AuthActivity
import org.junit.Rule

class IntTest {
    @Rule
    var mainActivityActivityTestRule: ActivityTestRule<AuthActivity> =
        object : ActivityTestRule<AuthActivity>(AuthActivity::class.java) {
        }
}