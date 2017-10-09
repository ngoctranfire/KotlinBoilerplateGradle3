package com.wakeupngoc.kotlinboilerplate.persistence.local.prefs

import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.wakeupngoc.kotlinboilerplate.definitions.auth.LoginState
import com.wakeupngoc.kotlinboilerplate.di.qualifiers.AppQualifier
import com.wakeupngoc.kotlinboilerplate.states.datamodel.LoginStateData
import javax.inject.Inject

object UserKeys {
    const val CURRENT_USER_EMAIL = "getCurrentUserEmail"
    const val LAST_LOGIN_TIME = "lastLoginTime"
    const val LOGIN_STATE = "loginState"
    const val LOGIN_ATTEMPT_COUNT = "loginAttemptCount"
}

interface UserPrefs {
    fun getCurrentUserEmail(): String
    fun getLastLoginTime(): Long
    fun getLoginState(): LoginState
    fun getLoginAttemptCount(): Int
    fun updateLoginStateData(loginStateData: LoginStateData)
}


class UserPrefsManager @Inject constructor (
        @AppQualifier private val rxSharedPreferences: RxSharedPreferences
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

    override fun getLoginAttemptCount(): Int {
        return rxSharedPreferences.getInteger(UserKeys.LOGIN_ATTEMPT_COUNT, 0).get()
    }

    override fun updateLoginStateData(loginStateData: LoginStateData) {
        val email: String = loginStateData.email
         with(rxSharedPreferences.getString(UserKeys.CURRENT_USER_EMAIL, "")) {
             if (get() != email) {
                 set(email)
             }
         }

        val loginState: String = loginStateData.loginState.name
        with(rxSharedPreferences.getString(UserKeys.LOGIN_STATE, "")) {
            if (get() != loginState) {
                set(loginState)
            }
        }

        val loginTime: Long = loginStateData.loginTime
        with(rxSharedPreferences.getLong(UserKeys.LAST_LOGIN_TIME, -1)) {
            if (get() != loginTime) {
                set(loginTime)
            }
        }
        with(rxSharedPreferences.getInteger(UserKeys.LOGIN_ATTEMPT_COUNT, 0)) {
            val loginAttemptCount: Int = get()
            if (LoginState.LOGGED_IN.name == loginState && loginAttemptCount != 0) {
                set(0)
            } else {
                set(loginAttemptCount+1)
            }
        }

    }


}