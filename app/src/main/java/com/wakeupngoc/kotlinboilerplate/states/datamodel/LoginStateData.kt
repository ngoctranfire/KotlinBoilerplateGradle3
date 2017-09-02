package com.wakeupngoc.kotlinboilerplate.states.datamodel

import com.wakeupngoc.kotlinboilerplate.definitions.auth.LoginState

/**
 * Created by ngoctranfire on 8/10/17.
 */
data class LoginStateData(val email: String,
                          val loginState: LoginState,
                          val loginTime: Long)