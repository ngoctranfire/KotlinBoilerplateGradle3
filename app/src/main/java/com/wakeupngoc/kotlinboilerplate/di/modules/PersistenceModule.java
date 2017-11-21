package com.wakeupngoc.kotlinboilerplate.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.wakeupngoc.kotlinboilerplate.R;
import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope;
import com.wakeupngoc.kotlinboilerplate.persistence.local.db.BoilerplateDB;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ngoctranfire on 8/13/17.
 */

@AppScope @Module
public class PersistenceModule {

    @AppScope @Provides
    static BoilerplateDB provideBoilerplateDB(Application app) {
        return Room.databaseBuilder(app, BoilerplateDB.class, app.getString(R.string.app_name)).build();
    }

}
