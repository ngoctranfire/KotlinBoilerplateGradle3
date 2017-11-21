package com.wakeupngoc.kotlinboilerplate.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager

import com.wakeupngoc.kotlinboilerplate.app.MainApplication
import com.wakeupngoc.kotlinboilerplate.di.components.DaggerAppComponent

import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

/**
 * Created by ngoctranfire on 11/9/17.
 */

object AppInjector {
    fun init(mainApplication: MainApplication) {
        DaggerAppComponent
                .builder()
                .app(mainApplication)
                .build()
                .inject(mainApplication)
        mainApplication.registerActivityLifecycleCallbacks(AppLifecycleCallbacks())
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
        }
        (activity as? FragmentActivity)?.supportFragmentManager?.registerFragmentLifecycleCallbacks(
                object : FragmentManager.FragmentLifecycleCallbacks() {
                    override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
                        super.onFragmentCreated(fm, f, savedInstanceState)
                        if (f is Injectable) {
                            AndroidSupportInjection.inject(f)
                        }
                    }
                }, true)
    }

    class AppLifecycleCallbacks: Application.ActivityLifecycleCallbacks {


        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            handleActivity(activity)
        }

        override fun onActivityStarted(activity: Activity) {}

        override fun onActivityResumed(activity: Activity) {}

        override fun onActivityPaused(activity: Activity) {}

        override fun onActivityStopped(activity: Activity) {}

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {}

        override fun onActivityDestroyed(activity: Activity) {}

    }
}
