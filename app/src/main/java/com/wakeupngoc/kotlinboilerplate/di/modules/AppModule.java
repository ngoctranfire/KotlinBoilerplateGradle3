package com.wakeupngoc.kotlinboilerplate.di.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.wakeupngoc.kotlinboilerplate.di.qualifiers.AppSharedPreferences;
import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ngoctranfire on 8/11/17.
 */

@AppScope
@Module
abstract public class AppModule {

    @Provides @AppScope @AppSharedPreferences
    static SharedPreferences provideDefaultSharedPreferences(Application app) {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }
}

