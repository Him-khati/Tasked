package com.himanshu.tasked.core.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

class AnalyticsHelperImpl @Inject constructor(
    private val analytics: FirebaseAnalytics
) : AnalyticsHelper {

    override fun setUser(userIdentifier: String) {
        analytics.setUserId(userIdentifier)
    }

    override fun resetAnalyticsData() {
        analytics.resetAnalyticsData()
    }

    override fun logEvent(event: String, data: Bundle?) {
        analytics.logEvent(event, data)
    }
}