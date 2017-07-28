package com.wakeupngoc.boilerplate.logs

import android.util.Log
import timber.log.Timber

class LogTree : Timber.Tree() {

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return false
        }
        return true
    }

    override fun log(priority: Int, tag: String, message: String, t: Throwable) {
        if (isLoggable(tag, priority)) {
            if (priority == Log.ASSERT) {
                Log.wtf(tag, message)
            } else {
                Log.println(priority, tag, message)
            }
        }
    }

}