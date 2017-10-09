package com.wakeupngoc.kotlinboilerplate.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.wakeupngoc.kotlinboilerplate.app.MainApplication
import com.wakeupngoc.kotlinboilerplate.di.components.AppComponent
import com.wakeupngoc.kotlinboilerplate.di.components.DaggerAppComponent
import com.wakeupngoc.kotlinboilerplate.ui.base.activity.BaseActivity
import dagger.android.AndroidInjection


/**
 * Created by ngoctranfire on 9/7/17.
 */
object AppInjector {

    fun initialize(app: MainApplication) {
        val appComponent: AppComponent = DaggerAppComponent
                .builder()
                .app(app)
                .build()

        appComponent.inject(app)

        app.registerActivityLifecycleCallbacks(object: Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {
                handleActivityInjection(activity!!)
            }
            override fun onActivityStarted(activity: Activity?) {}
            override fun onActivityResumed(activity: Activity?) {}
            override fun onActivityPaused(activity: Activity?) {}
            override fun onActivitySaveInstanceState(activity: Activity?, bundle: Bundle?) {}
            override fun onActivityStopped(activity: Activity?) {}
            override fun onActivityDestroyed(activity: Activity?) {}
        })
    }

    private fun handleActivityInjection(activity: Activity) {
        if (activity is BaseActivity) {
            AndroidInjection.inject(activity)
        } else {
            throw IllegalStateException(" Cannot inject an activity that is not an instance of the Base Activity")
        }
    }
}
