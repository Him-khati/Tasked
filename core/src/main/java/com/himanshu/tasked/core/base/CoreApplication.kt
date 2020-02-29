package com.himanshu.tasked.core.base

import android.app.Application
import com.himanshu.tasked.core.di.CoreComponent
import com.himanshu.tasked.core.di.DaggerCoreComponent
import com.himanshu.tasked.core.logger.LoggerImpl
import javax.inject.Inject

open class CoreApplication constructor(
    private val appInfo: AppInfo
) : Application() {

    @Inject
    lateinit var logger: LoggerImpl

    private lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        initLogger()
        initOrGetCoreDependency()
    }

    /**
     * Initialises And Returns [CoreComponent], this function will be used by
     * activity to create their Dagger Component
     */
    fun initOrGetCoreDependency(): CoreComponent {
        if (::coreComponent.isInitialized.not()) {
            coreComponent = DaggerCoreComponent
                .builder()
                .application(this)
                .build()

            coreComponent.injectApplication(this)
        }
        return coreComponent
    }

    private fun initLogger() {
        logger.init(appInfo.isDebugBuild)
    }
}