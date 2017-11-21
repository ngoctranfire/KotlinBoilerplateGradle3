package com.wakeupngoc.kotlinboilerplate.di.modules

import com.wakeupngoc.kotlinboilerplate.ui.auth.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by ngoctranfire on 11/9/17.
 */

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun loginActivity(): LoginActivity
}
