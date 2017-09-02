package com.wakeupngoc.kotlinboilerplate.ui.auth

import com.wakeupngoc.kotlinboilerplate.definitions.auth.LoginState
import com.wakeupngoc.kotlinboilerplate.services.analytics.Analytics
import com.wakeupngoc.kotlinboilerplate.states.PreAuthUserState
import com.wakeupngoc.kotlinboilerplate.states.datamodel.LoginStateData
import com.wakeupngoc.kotlinboilerplate.ui.auth.models.display.LoginDisplayState
import com.wakeupngoc.kotlinboilerplate.ui.auth.models.input.UserLoginInputModel
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

interface LoginVM {
    fun getLoginDisplayState(userLoginInputModel: Observable<UserLoginInputModel>): Observable<LoginDisplayState>
}

class LoginVMImpl @Inject constructor(
        private val analytics: Analytics,
        private val preAuthUserState: PreAuthUserState
): LoginVM {

    init {
        Timber.d("LoginVMImpl initialized")
    }

    override fun getLoginDisplayState(userLoginInputModel: Observable<UserLoginInputModel>): Observable<LoginDisplayState> {
//        return userLoginInputModel
//                .switchMap{
////                    inputModel -> Observable.combineLatest(loginCredentialsValid(inputModel), preAuthUserState.getUserState())
//                }
        return Observable.just(LoginDisplayState(false, LoginState.LOGGED_OUT))
    }

    private fun loginCredentialsValid(userLoginInputModel: UserLoginInputModel): Observable<Boolean> {
        return Observable.just(!(userLoginInputModel.email.isBlank() || userLoginInputModel.password.isBlank()))
    }

    private fun setUserState(loginStateData: LoginStateData): Unit {
        preAuthUserState.updateUserState(loginStateData)
    }

}