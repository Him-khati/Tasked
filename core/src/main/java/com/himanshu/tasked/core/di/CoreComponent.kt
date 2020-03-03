package com.himanshu.tasked.core.di

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.himanshu.tasked.core.analytics.AnalyticsHelper
import com.himanshu.tasked.core.di.modules.ContextModule
import com.himanshu.tasked.core.di.modules.CoreModule
import com.himanshu.tasked.data.di.DataModule
import com.vinners.core.logger.Logger
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        CoreModule::class,
        ContextModule::class
    ]
)
interface CoreComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CoreComponent
    }

    fun injectApplication(application: Application)

    fun getLogger(): Logger

    fun getAnalyticsHelper(): AnalyticsHelper

    fun getFirebaseAnalytics(): FirebaseAnalytics

    fun getFirebaseAuth(): FirebaseAuth
}