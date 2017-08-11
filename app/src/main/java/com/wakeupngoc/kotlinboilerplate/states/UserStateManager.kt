package com.wakeupngoc.kotlinboilerplate.states

import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope
import com.wakeupngoc.kotlinboilerplate.states.datamodel.UserData
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * Created by ngoctranfire on 8/6/17.
 */

@AppScope
interface UserState {
    fun updateUserState(userData: UserData)
}

@AppScope
class UserStateManager @Inject constructor(): UserState {

    private val userState: BehaviorSubject<UserData> = BehaviorSubject.create()

    init {
        bindStreams()
    }

    private fun bindStreams(): Unit {
        // User State Management
        userState.serialize()
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    override fun updateUserState(userData: UserData): Unit {
        userState.onNext(userData)
    }

}