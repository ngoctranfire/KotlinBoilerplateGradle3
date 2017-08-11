package com.wakeupngoc.kotlinboilerplate.ui.login.state;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static com.wakeupngoc.kotlinboilerplate.ui.login.state.LoginState.LOGGED_IN;
import static com.wakeupngoc.kotlinboilerplate.ui.login.state.LoginState.LOGGED_OUT;
import static com.wakeupngoc.kotlinboilerplate.ui.login.state.LoginState.LOGGING_IN;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@StringDef({
    LOGGED_OUT,
    LOGGING_IN,
    LOGGED_IN
})
public @interface LoginState {
    String LOGGED_OUT = "logged_out";
    String LOGGING_IN = "logging_in";
    String LOGGED_IN = "logged_in";
}
