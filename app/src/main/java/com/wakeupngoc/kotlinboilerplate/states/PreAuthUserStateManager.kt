package com.wakeupngoc.kotlinboilerplate.states

import com.wakeupngoc.kotlinboilerplate.persistence.prefs.UserPrefsManager
import com.wakeupngoc.kotlinboilerplate.states.datamodel.LoginStateData
import dagger.Reusable
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * Created by ngoctranfire on 8/6/17.
 */

@Reusable
interface PreAuthUserState {
    fun updateUserState(loginStateData: LoginStateData)
    fun getUserState(): Observable<LoginStateData>
}

@Reusable
class PreAuthUserStateManager @Inject constructor(private val userPrefsManager: UserPrefsManager): PreAuthUserState {

    private val loginStateData: BehaviorSubject<LoginStateData> by lazy {
        BehaviorSubject.createDefault(LoginStateData(userPrefsManager.getCurrentUserEmail(), userPrefsManager.getLoginState(),
                userPrefsManager.getLastLoginTime()))
    }

    init {
        bindStreams()
    }

    private fun bindStreams() {
        // User State Management
        bindUserState()
    }

    private fun bindUserState() {
        loginStateData
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribeBy(onNext = {
                    loginStateData -> userPrefsManager.updateLoginStateData(loginStateData)
                })
    }

    override fun getUserState(): Observable<LoginStateData> {
        return loginStateData
    }


    override fun updateUserState(loginStateData: LoginStateData) {
        this.loginStateData.onNext(loginStateData)
    }


}