package com.wakeupngoc.kotlinboilerplate.di.modules;

import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope;
import com.wakeupngoc.kotlinboilerplate.ui.auth.LoginActivity;
import com.wakeupngoc.kotlinboilerplate.ui.auth.LoginActivityModule;

import dagger.Module;

import dagger.android.ContributesAndroidInjector;

/**
 * Created by ngoctranfire on 8/11/17.
 */

@AppScope
@Module
abstract public class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity mainActivity();

}
