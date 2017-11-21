package com.wakeupngoc.kotlinboilerplate.di.components

import com.wakeupngoc.kotlinboilerplate.di.scopes.ActScope
import com.wakeupngoc.kotlinboilerplate.ui.auth.LoginActivity

import dagger.Subcomponent

/**
 * Created by ngoctranfire on 10/8/17.
 */
@ActScope
@Subcomponent
interface ActComponent {

    fun inject(loginActivity: LoginActivity)
}