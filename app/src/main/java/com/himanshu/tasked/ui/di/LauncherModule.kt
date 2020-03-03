package com.himanshu.tasked.ui.di

import com.himanshu.tasked.core.di.scopes.FeatureScope
import com.himanshu.tasked.core.extensions.viewModel
import com.himanshu.tasked.data.sessionManagement.UserSessionManager
import com.himanshu.tasked.ui.launcher.LauncherActivity
import com.himanshu.tasked.ui.launcher.LauncherViewModel
import dagger.Module
import dagger.Provides

@Module
class LauncherModule(
    val activity: LauncherActivity
) {

    @Provides
    @FeatureScope
    fun providesLauncherViewModel(
        userSessionManager: UserSessionManager
    ) = activity.viewModel {
        LauncherViewModel(
            userSessionManager = userSessionManager
        )
    }
}