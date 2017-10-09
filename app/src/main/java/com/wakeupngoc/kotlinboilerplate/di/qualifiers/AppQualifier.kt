package com.wakeupngoc.kotlinboilerplate.di.qualifiers

/**
 * Created by ngoctranfire on 5/17/17.
 */

import javax.inject.Qualifier

import kotlin.annotation.AnnotationRetention
import kotlin.annotation.Retention

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppQualifier