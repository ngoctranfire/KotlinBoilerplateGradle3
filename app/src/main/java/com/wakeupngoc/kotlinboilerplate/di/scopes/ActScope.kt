package com.wakeupngoc.kotlinboilerplate.di.scopes

/**
 * Created by ngoctranfire on 5/17/17.
 */

import javax.inject.Scope

import kotlin.annotation.AnnotationRetention
import kotlin.annotation.Retention

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActScope