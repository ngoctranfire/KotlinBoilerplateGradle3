package com.wakeupngoc.kotlinboilerplate.ui.auth.models.actions

import com.wakeupngoc.kotlinboilerplate.ui.base.actions.Action

/**
 * Created by ngoctranfire on 9/3/17.
 */
data class SubmitAction(val email: String, val password: String) : Action()
data class LoginInputAction(val email: String, val password: String): Action()