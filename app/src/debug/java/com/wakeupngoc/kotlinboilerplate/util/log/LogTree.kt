package com.wakeupngoc.kotlinboilerplate.util.log

import timber.log.Timber

class LogTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        val tag = super.createStackElementTag(element)

        var length = tag.length
        if (tag.indexOf("$") > -1) {
            length = tag.indexOf("$")
        }
        return "*Boilerplate*" + " (" + tag.substring(0, length) + ".kt:" + element.lineNumber + ")" + tag.substring(length)
    }
}