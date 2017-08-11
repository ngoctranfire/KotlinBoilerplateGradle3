package com.wakeupngoc.kotlinboilerplate.di.modules;

import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope;
import com.wakeupngoc.kotlinboilerplate.ui.main.MainActivity;
import com.wakeupngoc.kotlinboilerplate.ui.main.MainActivityModule;

import dagger.Module;

import dagger.android.ContributesAndroidInjector;

/**
 * Created by ngoctranfire on 8/11/17.
 */

@AppScope
@Module
abstract public class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();

}
