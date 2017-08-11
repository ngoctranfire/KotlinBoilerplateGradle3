package com.wakeupngoc.kotlinboilerplate.di.modules;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope;
import com.wakeupngoc.kotlinboilerplate.services.analytics.Analytics;
import com.wakeupngoc.kotlinboilerplate.services.analytics.CrashAnalytics;
import com.wakeupngoc.kotlinboilerplate.services.analytics.CrashReporter;
import com.wakeupngoc.kotlinboilerplate.services.analytics.EventAnalytics;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ngoctranfire on 8/11/17.
 */
@AppScope
@Module(includes = AppReportsModule.Declarations.class)
abstract public class AppReportsModule {

    @Provides @AppScope
    static FirebaseApp provideFirebaseApp(Application application) {
        FirebaseApp.initializeApp(application);
        return FirebaseApp.getInstance();
    }

    @Provides @AppScope
    static FirebaseAnalytics provideFirebaseAnalytics(Application app) {
        return FirebaseAnalytics.getInstance(app);
    }

    @Module @AppScope
    public interface Declarations {
        @Binds @AppScope
        CrashReporter provideCrashReporter(CrashReporter crashReporter);

        @Binds @AppScope
        Analytics provideAnalytics(EventAnalytics eventAnalytics);

        @Binds @AppScope
        CrashAnalytics provideCrashAnalytics(CrashReporter crashReporter);
    }
}
