package com.wakeupngoc.kotlinboilerplate.ui.auth.models.display

import com.wakeupngoc.kotlinboilerplate.definitions.auth.LoginState

/**
 * Created by ngoctranfire on 8/10/17.
 */
data class LoginDisplayState(val btnState: Boolean, val loginState: LoginState, val error: Throwable?, val loginAttempt: Int = 0) {
    companion object {
        fun INITIAL_STATE() : LoginDisplayState {
            return LoginDisplayState(false, LoginState.LOGGED_OUT, null, 0)
        }

    }
}

