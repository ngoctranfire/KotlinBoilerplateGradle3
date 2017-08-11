package com.wakeupngoc.kotlinboilerplate.ui.main

import com.wakeupngoc.kotlinboilerplate.services.analytics.Analytics
import com.wakeupngoc.kotlinboilerplate.states.UserState
import com.wakeupngoc.kotlinboilerplate.states.datamodel.UserData
import com.wakeupngoc.kotlinboilerplate.ui.main.models.input.UserLoginInputModel
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

interface MainVM {
    fun getButtonState(userLoginInputModel: Observable<UserLoginInputModel>): Observable<Boolean>
}

class MainVMImpl @Inject constructor(
        private val analytics: Analytics,
        private val userState: UserState
):MainVM {

    init {
        Timber.d( "Hey I got in here to constructor again, MainVM()!")
    }

    override fun getButtonState(userLoginInputModel: Observable<UserLoginInputModel>): Observable<Boolean> {
        return userLoginInputModel
                .switchMap(this::isButtonDisabled)
    }

    private fun isButtonDisabled(userLoginInputModel: UserLoginInputModel): Observable<Boolean> {
        return Observable.just(
                !(userLoginInputModel.email.isBlank() || userLoginInputModel.password.isBlank())
        )
    }

    private fun updateUserState(userData: UserData): Unit {
        userState.updateUserState(userData)
    }

}