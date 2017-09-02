package com.wakeupngoc.kotlinboilerplate.di.modules;

import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope;
import com.wakeupngoc.kotlinboilerplate.states.PreAuthUserState;
import com.wakeupngoc.kotlinboilerplate.states.PreAuthUserStateManager;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;

/**
 * Created by ngoctranfire on 8/11/17.
 */
@Module @AppScope
public abstract class UserModule {
    @Binds @Reusable
    abstract PreAuthUserState bindUserStateManager(PreAuthUserStateManager preAuthUserStateManager);
}
