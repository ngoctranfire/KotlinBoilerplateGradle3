package com.wakeupngoc.kotlinboilerplate.util.log

import timber.log.Timber

class LogTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "*Boilerplate*" + " (" + super.createStackElementTag(element) + ".kt:" + element.lineNumber + ")"
    }
}