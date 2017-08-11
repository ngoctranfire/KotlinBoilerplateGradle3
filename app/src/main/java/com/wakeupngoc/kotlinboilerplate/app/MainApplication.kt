package com.wakeupngoc.kotlinboilerplate.app

import android.app.Activity
import android.app.Application
import android.util.Log
import com.facebook.stetho.Stetho
import com.google.firebase.FirebaseApp
import com.squareup.leakcanary.LeakCanary
import com.wakeupngoc.kotlinboilerplate.BuildConfig
import com.wakeupngoc.kotlinboilerplate.di.components.DaggerAppComponent
import com.wakeupngoc.kotlinboilerplate.util.log.LogTree
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject
import dagger.android.DispatchingAndroidInjector

/**
 * Created by ngoctranfire on 5/20/17.
 */
class MainApplication : Application(), HasActivityInjector{

    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        DaggerAppComponent
                .builder()
                .app(this)
                .build()
                .inject(this)

        super.onCreate()

        // LogTree dependent on what environment we are using.

        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return
            }
            LeakCanary.install(this)
            Log.d("MainApplication", "Installing leak Canary!")
            Stetho.initializeWithDefaults(this)

        }

        FirebaseApp.initializeApp(this)

        Timber.plant(LogTree())
        Timber.d("Creating MainApplication DAG")


    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

}
