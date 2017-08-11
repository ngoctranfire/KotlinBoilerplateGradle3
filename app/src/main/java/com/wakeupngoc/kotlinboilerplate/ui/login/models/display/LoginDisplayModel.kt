package com.wakeupngoc.kotlinboilerplate.ui.login.models.display

import com.wakeupngoc.kotlinboilerplate.ui.login.state.LoginState

/**
 * Created by ngoctranfire on 8/10/17.
 */
data class LoginDisplayModel(val btnState: Boolean, @LoginState val loginState: String )