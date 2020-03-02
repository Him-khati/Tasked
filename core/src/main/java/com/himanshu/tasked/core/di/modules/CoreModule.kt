package com.himanshu.tasked.core.di.modules

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.himanshu.tasked.core.analytics.AnalyticsHelper
import com.himanshu.tasked.core.analytics.AnalyticsHelperImpl
import com.himanshu.tasked.core.logger.LoggerImpl
import com.vinners.core.logger.Logger
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class CoreModule {

    @Module
    companion object {

        @Singleton
        @Provides
        fun provideFirebaseAnalytics(context: Context): FirebaseAnalytics {
            return FirebaseAnalytics.getInstance(context)
        }

        @Singleton
        @Provides
        fun provideLogger(): Logger {
            return LoggerImpl()
        }
    }

    @Singleton
    @Binds
    abstract fun bindAnalyticsHelper(analyticsHelperImpl: AnalyticsHelperImpl): AnalyticsHelper
}