package com.wakeupngoc.kotlinboilerplate.app

import android.app.Application
import com.wakeupngoc.kotlinboilerplate.di.modules.AppModule
import com.wakeupngoc.kotlinboilerplate.di.components.AppComponent
import com.wakeupngoc.kotlinboilerplate.di.components.DaggerAppComponent
import com.wakeupngoc.kotlinboilerplate.util.log.LogTree
import timber.log.Timber

/**
 * Created by ngoctranfire on 5/20/17.
 */
class MainApplication : Application() {
    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }
    override fun onCreate() {
        super.onCreate()
        // LogTree dependent on what environment we are using.
        Timber.plant(LogTree())
        Timber.d("Creating MainApplication DAG")
        appComponent = DaggerAppComponent
                .builder()
                .build()
    }

    private fun getAppModule(): AppModule {
        return AppModule(this)
    }
}
