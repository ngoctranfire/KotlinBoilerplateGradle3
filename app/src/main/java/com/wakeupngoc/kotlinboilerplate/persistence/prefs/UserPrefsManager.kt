package com.wakeupngoc.kotlinboilerplate.persistence.prefs

import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.wakeupngoc.kotlinboilerplate.definitions.auth.LoginState
import com.wakeupngoc.kotlinboilerplate.di.qualifiers.AppSharedPreferences
import com.wakeupngoc.kotlinboilerplate.states.datamodel.LoginStateData
import javax.inject.Inject

object UserKeys {
    const val CURRENT_USER_EMAIL = "getCurrentUserEmail"
    const val LAST_LOGIN_TIME = "lastLoginTime"
    const val LOGIN_STATE = "loginState"
}

interface UserPrefs {
    fun getCurrentUserEmail(): String
    fun getLastLoginTime(): Long
    fun getLoginState(): LoginState
    fun updateLoginStateData(loginStateData: LoginStateData)
}


class UserPrefsManager @Inject constructor (
        @AppSharedPreferences private val rxSharedPreferences: RxSharedPreferences
): UserPrefs {

    override fun getCurrentUserEmail(): String {
        return rxSharedPreferences.getString(UserKeys.CURRENT_USER_EMAIL, "").get()
    }

    override fun getLastLoginTime(): Long {
        return rxSharedPreferences.getLong(UserKeys.LAST_LOGIN_TIME, -1).get()
    }


    override fun getLoginState(): LoginState {
        return LoginState.valueOf(rxSharedPreferences.getString(UserKeys.LOGIN_STATE, LoginState.LOGGED_OUT.name).get())
    }

    override fun updateLoginStateData(loginStateData: LoginStateData) {
        val email: String = loginStateData.email
         with(rxSharedPreferences.getString(UserKeys.CURRENT_USER_EMAIL, email)) {
             if (get() != email) {
                 set(email)
             }
         }

        val loginState: String = loginStateData.loginState.name
        with(rxSharedPreferences.getString(UserKeys.LOGIN_STATE, loginState)) {
            if (get() != loginState) {
                set(loginState)
            }
        }

        val loginTime: Long = loginStateData.loginTime
        with(rxSharedPreferences.getLong(UserKeys.LAST_LOGIN_TIME, loginTime)) {
            if (get() != loginTime) {
                set(loginTime)
            }
        }
    }


}