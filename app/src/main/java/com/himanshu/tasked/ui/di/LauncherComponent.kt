package com.himanshu.tasked.ui.di

import com.himanshu.tasked.core.di.CoreComponent
import com.himanshu.tasked.core.di.scopes.FeatureScope
import com.himanshu.tasked.ui.launcher.LauncherActivity
import dagger.Component

@FeatureScope
@Component(
    modules = [LauncherModule::class],
    dependencies = [CoreComponent::class]
)
interface LauncherComponent {

    fun inject(launcherActivity: LauncherActivity)
}