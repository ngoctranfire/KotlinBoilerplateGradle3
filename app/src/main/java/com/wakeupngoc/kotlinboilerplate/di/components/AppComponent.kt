package com.wakeupngoc.kotlinboilerplate.di.components

import android.app.Application
import com.wakeupngoc.kotlinboilerplate.app.MainApplication
import com.wakeupngoc.kotlinboilerplate.di.modules.*
import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope
import com.wakeupngoc.kotlinboilerplate.ui.base.activity.BaseActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by ngoctranfire on 5/17/17.
 */

@AppScope
@Component(modules = arrayOf(
        AppModule::class,
        AppReportsModule::class,
        NetworkModule::class,
        PersistenceModule::class,
        UserModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        ActivityModule::class
))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun app(app: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: MainApplication)

    // Activities Injected
    fun inject(baseActivity: BaseActivity)
}