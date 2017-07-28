package com.wakeupngoc.kotlinboilerplate.di.components

import com.wakeupngoc.kotlinboilerplate.di.modules.AppModule
import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope
import com.wakeupngoc.kotlinboilerplate.ui.MainActivity
import dagger.Component

/**
 * Created by ngoctranfire on 5/17/17.
 */

@AppScope
@Component(modules = arrayOf(
        AppModule::class
))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}