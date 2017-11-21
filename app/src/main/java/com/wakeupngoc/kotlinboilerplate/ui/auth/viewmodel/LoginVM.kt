package com.wakeupngoc.kotlinboilerplate.ui.auth.viewmodel

import android.arch.lifecycle.ViewModel
import com.wakeupngoc.kotlinboilerplate.ui.auth.models.display.LoginDisplayState
import com.wakeupngoc.kotlinboilerplate.ui.base.actions.Action
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by ngoctranfire on 10/9/17.
 */
abstract class LoginVM : ViewModel() {
    abstract fun getLoginDisplayState(action: Observable<Action>): Observable<LoginDisplayState>
    abstract fun getLastInputEmail(): Single<String>
}
