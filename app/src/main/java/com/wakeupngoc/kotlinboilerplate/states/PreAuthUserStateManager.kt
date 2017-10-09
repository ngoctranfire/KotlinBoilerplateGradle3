package com.wakeupngoc.kotlinboilerplate.states

import com.wakeupngoc.kotlinboilerplate.persistence.local.prefs.UserPrefsManager
import com.wakeupngoc.kotlinboilerplate.states.datamodel.LoginStateData
import dagger.Reusable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * Created by ngoctranfire on 8/6/17.
 */

@Reusable
interface PreAuthUserState {
    fun updateLoginStateData(loginStateData: LoginStateData)
    fun getLoginAttemptCount(): Single<Int>
    fun getLoginStateData(): Observable<LoginStateData>
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
                .serialize()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribeBy(onNext = {
                    loginStateData -> userPrefsManager.updateLoginStateData(loginStateData)
                })
    }

    override fun getLoginStateData(): Observable<LoginStateData> {
        return loginStateData
    }

    override fun getLoginAttemptCount(): Single<Int> {
        return Single.fromCallable {
            userPrefsManager.getLoginAttemptCount()
        }
    }

    override fun updateLoginStateData(loginStateData: LoginStateData) {
        this.loginStateData.onNext(loginStateData)
    }


}