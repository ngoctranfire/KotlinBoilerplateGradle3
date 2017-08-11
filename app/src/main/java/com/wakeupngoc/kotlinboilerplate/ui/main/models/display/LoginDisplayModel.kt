package com.wakeupngoc.kotlinboilerplate.ui.main.models.display

import com.wakeupngoc.kotlinboilerplate.ui.main.state.LoginState

/**
 * Created by ngoctranfire on 8/10/17.
 */
data class LoginDisplayModel(val btnState: Boolean, @LoginState val loginState: String )