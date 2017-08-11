package com.wakeupngoc.kotlinboilerplate.di.modules;

import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope;
import com.wakeupngoc.kotlinboilerplate.states.UserState;
import com.wakeupngoc.kotlinboilerplate.states.UserStateManager;

import dagger.Binds;
import dagger.Module;

/**
 * Created by ngoctranfire on 8/11/17.
 */
@Module @AppScope
public abstract class UserModule {
    @Binds @AppScope
    abstract UserState bindUserStateManager(UserStateManager userStateManager);
}
