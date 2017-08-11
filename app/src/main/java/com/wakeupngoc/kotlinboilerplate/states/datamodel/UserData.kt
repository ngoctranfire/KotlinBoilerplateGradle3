package com.wakeupngoc.kotlinboilerplate.states.datamodel

import com.wakeupngoc.kotlinboilerplate.ui.login.state.LoginState

/**
 * Created by ngoctranfire on 8/10/17.
 */
data class UserData(val email: String, @LoginState val loginState: String)