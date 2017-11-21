package com.wakeupngoc.kotlinboilerplate.app

import android.app.Activity
import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.squareup.leakcanary.LeakCanary
import com.wakeupngoc.kotlinboilerplate.BuildConfig
import timber.log.Timber
import android.os.StrictMode
import com.wakeupngoc.kotlinboilerplate.di.AppInjector
import com.wakeupngoc.kotlinboilerplate.util.log.LogTree
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by ngoctranfire on 5/20/17.
 */
class MainApplication : Application(), HasActivityInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)

//         com.wakeupngoc.kotlinboilerplate.util.log.LogTree dependent on what environment we are using.

        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return
            }
            LeakCanary.install(this)
            Log.d("MainApplication", "Installing leak Canary!")

            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build())

            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build())
        }

        FirebaseApp.initializeApp(this)

        Timber.plant(LogTree())
        Timber.d("Creating MainApplication DAG")
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

}
