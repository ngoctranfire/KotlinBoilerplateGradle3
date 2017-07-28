package com.wakeupngoc.kotlinboilerplate.di.modules

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.wakeupngoc.kotlinboilerplate.di.qualifiers.AppSharedPreferences
import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope
import dagger.Module
import dagger.Provides

/**
 * Created by ngoctranfire on 5/17/17.
 */
@Module
class AppModule(val application: Application) {

    @Provides @AppScope
    fun provideApplication() : Application = application

    @Provides @AppScope @AppSharedPreferences
    fun providesSharedPreferences() : SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

}