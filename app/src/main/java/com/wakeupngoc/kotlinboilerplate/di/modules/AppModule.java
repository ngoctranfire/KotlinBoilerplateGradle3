package com.wakeupngoc.kotlinboilerplate.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.wakeupngoc.kotlinboilerplate.app.AppConstants;
import com.wakeupngoc.kotlinboilerplate.app.PreferenceConstants;
import com.wakeupngoc.kotlinboilerplate.di.qualifiers.AppSharedPreferences;
import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope;
import com.wakeupngoc.kotlinboilerplate.persistence.prefs.UserPrefs;
import com.wakeupngoc.kotlinboilerplate.persistence.prefs.UserPrefsManager;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ngoctranfire on 8/11/17.
 */

@AppScope
@Module(includes = AppModule.Declarations.class)
abstract public class AppModule {

    @Provides @AppScope @AppSharedPreferences
    static SharedPreferences provideDefaultSharedPreferences(Application app) {
        return app.getSharedPreferences(PreferenceConstants.USER_PREFS, Context.MODE_PRIVATE);
    }

    @Provides @AppScope @AppSharedPreferences
    static RxSharedPreferences provideRxSharedPreferences(@AppSharedPreferences SharedPreferences sharedPreferences) {
        return RxSharedPreferences.create(sharedPreferences);
    }

    @Module
    public interface Declarations {
        @Binds
        UserPrefs provideUserPrefsManager(UserPrefsManager userPrefsManager);
    }
}
