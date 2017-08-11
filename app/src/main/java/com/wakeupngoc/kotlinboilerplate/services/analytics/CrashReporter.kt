package com.wakeupngoc.kotlinboilerplate.services.analytics

import timber.log.Timber
import javax.inject.Inject

/**
 * Created by ngoctranfire on 8/6/17.
 */

interface CrashAnalytics {
    fun report(throwable: Throwable)
}

class CrashReporter @Inject constructor(): CrashAnalytics {

    init {
    }

    override fun report(throwable: Throwable) {
        return Timber.e(throwable)
    }

}