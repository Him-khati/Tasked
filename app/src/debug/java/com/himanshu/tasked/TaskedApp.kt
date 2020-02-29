package com.himanshu.tasked

import com.facebook.stetho.Stetho
import com.himanshu.tasked.core.base.AppInfo
import com.himanshu.tasked.core.base.CoreApplication

val DEBUG_APP_INFO = AppInfo(
    isDebugBuild = true
)

class TaskedApp : CoreApplication(
    appInfo = DEBUG_APP_INFO
) {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}