package com.wakeupngoc.kotlinboilerplate.util.time

import android.os.SystemClock
import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope
import javax.inject.Inject

/**
 * Created by ngoctranfire on 9/3/17.
 */

interface TimeOperations {
    fun realTimeMs(): Long
}

@AppScope
class TimeUtils : TimeOperations {
    override fun realTimeMs(): Long {
        return SystemClock.elapsedRealtime()
    }
}