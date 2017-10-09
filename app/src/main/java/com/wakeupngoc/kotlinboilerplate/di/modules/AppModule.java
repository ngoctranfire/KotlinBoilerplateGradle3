package com.wakeupngoc.kotlinboilerplate.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.wakeupngoc.kotlinboilerplate.constants.PreferenceConstants;
import com.wakeupngoc.kotlinboilerplate.di.qualifiers.AppQualifier;
import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope;
import com.wakeupngoc.kotlinboilerplate.persistence.local.prefs.UserPrefs;
import com.wakeupngoc.kotlinboilerplate.persistence.local.prefs.UserPrefsManager;
import com.wakeupngoc.kotlinboilerplate.persistence.remote.service.AuthService;
import com.wakeupngoc.kotlinboilerplate.util.time.TimeOperations;
import com.wakeupngoc.kotlinboilerplate.util.time.TimeUtils;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by ngoctranfire on 8/11/17.
 */

@AppScope
@Module(includes = AppModule.Declarations.class )
abstract public class AppModule {

    @Provides @AppScope @AppQualifier
    static SharedPreferences provideDefaultSharedPreferences(Application app) {
        return app.getSharedPreferences(PreferenceConstants.USER_PREFS, Context.MODE_PRIVATE);
    }

    @Provides @AppScope @AppQualifier
    static RxSharedPreferences provideRxSharedPreferences(@AppQualifier SharedPreferences sharedPreferences) {
        return RxSharedPreferences.create(sharedPreferences);
    }

    @Provides @AppScope
    static TimeOperations provideTimeUtils() {
        return new TimeUtils();
    }


    @Module
    public interface Declarations {
        @Binds
        UserPrefs provideUserPrefsManager(UserPrefsManager userPrefsManager);
    }
}
