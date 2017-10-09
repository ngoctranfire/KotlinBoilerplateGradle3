package com.wakeupngoc.kotlinboilerplate.ui.auth

import android.arch.lifecycle.ViewModel
import com.wakeupngoc.kotlinboilerplate.constants.AuthStrategy
import com.wakeupngoc.kotlinboilerplate.definitions.auth.LoginState
import com.wakeupngoc.kotlinboilerplate.persistence.remote.service.AuthService
import com.wakeupngoc.kotlinboilerplate.services.analytics.Analytics
import com.wakeupngoc.kotlinboilerplate.states.PreAuthUserState
import com.wakeupngoc.kotlinboilerplate.states.datamodel.LoginStateData
import com.wakeupngoc.kotlinboilerplate.ui.auth.models.actions.LoginInputAction
import com.wakeupngoc.kotlinboilerplate.ui.auth.models.actions.SubmitAction
import com.wakeupngoc.kotlinboilerplate.ui.auth.models.display.LoginDisplayState
import com.wakeupngoc.kotlinboilerplate.ui.auth.models.result.LoginInputResult
import com.wakeupngoc.kotlinboilerplate.ui.auth.models.result.SubmitResult
import com.wakeupngoc.kotlinboilerplate.ui.base.actions.Action
import com.wakeupngoc.kotlinboilerplate.ui.base.result.Result
import com.wakeupngoc.kotlinboilerplate.util.time.TimeOperations
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

abstract class LoginVM : ViewModel() {
    abstract fun getLoginDisplayState(action: Observable<Action>): Observable<LoginDisplayState>
    abstract fun getLastInputEmail(): Single<String>
}

class LoginVMImpl @Inject constructor(
        private val timeOperations: TimeOperations,
        private val analytics: Analytics,
        private val preAuthUserState: PreAuthUserState,
        private val authService: AuthService
): LoginVM(){

    init {
        Timber.d("LoginVMImpl initialized")
    }


    override fun getLoginDisplayState(action: Observable<Action>): Observable<LoginDisplayState> {
        return action.compose(transformer)
    }

    override fun getLastInputEmail(): Single<String> {
        return preAuthUserState.getLoginStateData()
                .map { (email) -> email }
                .first("")
    }


    private val transformer: ObservableTransformer<Action, LoginDisplayState> = ObservableTransformer { actions ->
        actions.publish {
            shared -> Observable.merge(
                shared.ofType(LoginInputAction::class.java).compose(loginInputTransformer),
                shared.ofType(SubmitAction::class.java).compose(submitTransformer)
            )
        }.scan(LoginDisplayState.INITIAL_STATE(), {
            previous, result -> stateReducer(previous, result)
        })
    }

    private val loginInputTransformer: ObservableTransformer<LoginInputAction, Result> = ObservableTransformer { inputAction ->
        inputAction.map {
            input -> LoginInputResult(isValidCredentials(input))
        }
    }

    private val submitTransformer: ObservableTransformer<SubmitAction, Result> = ObservableTransformer { submitAction ->
        submitAction
                .switchMap { (email, password) ->
                    authService.authenticate(email, password, AuthStrategy.LOCAL)
                            .delay(2, TimeUnit.SECONDS)
                            .doOnSubscribe { preAuthUserState.updateLoginStateData(loginStateData = LoginStateData(email, LoginState.LOGGING_IN, timeOperations.realTimeMs())) }
                            .doOnSuccess {
                                preAuthUserState.updateLoginStateData(loginStateData = LoginStateData(email, LoginState.LOGGED_IN, timeOperations.realTimeMs()))
                            }
                            .flatMapObservable { authResponse ->
                                Timber.v(" authResponse=$authResponse")
                                Observable.just(SubmitResult.SUCCESS())
                            }
                            .onErrorReturn { e -> SubmitResult.FAILED(e) }
                            .startWith(SubmitResult.IN_PROGRESS())
                            .subscribeOn(Schedulers.io())
                }
    }

    private fun isValidCredentials(loginInputAction: LoginInputAction): Boolean {
        return !(loginInputAction.email.isBlank() || loginInputAction.password.isBlank())
    }

    /**
     * Reducer is pure. No side effects. Should only take in a previous state and a result and display a new state!
     */
    private fun stateReducer(previousState: LoginDisplayState, result: Result): LoginDisplayState {
        val newState: LoginDisplayState = when (result) {
            is LoginInputResult -> {
                reduceLoginResulToDisplayState(previousState, result)
            }
            is SubmitResult -> {
                reduceSubmitResultToDisplayState(previousState, result)
            }
            else -> {
                previousState
            }
        }
        Timber.v("previousState=$previousState, result=$result, newState=$newState")
        return newState
    }

    private fun reduceLoginResulToDisplayState(prevState: LoginDisplayState, result: LoginInputResult): LoginDisplayState {
        return prevState.copy(btnState = result.submitEnabled, loginState = LoginState.LOGGED_OUT, error = null)
    }

    private fun reduceSubmitResultToDisplayState(prevState: LoginDisplayState, result:SubmitResult): LoginDisplayState {
        var loginAttempt : Int = prevState.loginAttempt
        val loginState: LoginState

        when {
            result.inProgress -> loginState = LoginState.LOGGING_IN
            result.success -> loginState = LoginState.LOGGED_IN
            result.error != null ->  {
                loginState = LoginState.LOGGED_OUT
                loginAttempt++
            }
            else -> loginState = LoginState.LOGGED_OUT
        }

        return prevState.copy(btnState = !result.inProgress, loginState = loginState,
                error = result.error, loginAttempt = loginAttempt)
    }


}