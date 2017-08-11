package com.wakeupngoc.kotlinboilerplate.services.analytics

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.Size
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

interface Analytics {
    fun logEvent(@Size(min = 1L, max = 40L) eventName: String, bundle: Bundle)
}

class EventAnalytics @Inject constructor(
        private val analytics: FirebaseAnalytics
): Analytics {

    @SuppressLint("Range")
    override fun logEvent(@Size(min = 1L, max = 40L) eventName: String, bundle: Bundle) {
        analytics.logEvent(eventName, bundle)
    }
}