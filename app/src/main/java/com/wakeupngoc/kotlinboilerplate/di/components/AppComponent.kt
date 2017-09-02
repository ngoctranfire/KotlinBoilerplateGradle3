package com.wakeupngoc.kotlinboilerplate.di.components

import android.app.Application
import com.wakeupngoc.kotlinboilerplate.app.MainApplication
import com.wakeupngoc.kotlinboilerplate.di.modules.*
import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

/**
 * Created by ngoctranfire on 5/17/17.
 */

@AppScope
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        AppReportsModule::class,
        PersistenceModule::class,
        UserModule::class,
        ActivityBuilderModule::class
))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun app(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MainApplication)
}