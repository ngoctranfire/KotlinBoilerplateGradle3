package com.wakeupngoc.kotlinboilerplate.ui.auth

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.UiThread
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.wakeupngoc.kotlinboilerplate.R
import com.wakeupngoc.kotlinboilerplate.definitions.auth.LoginState
import com.wakeupngoc.kotlinboilerplate.ext.into
import com.wakeupngoc.kotlinboilerplate.ui.auth.models.actions.LoginInputAction
import com.wakeupngoc.kotlinboilerplate.ui.auth.models.actions.SubmitAction
import com.wakeupngoc.kotlinboilerplate.ui.auth.viewmodel.LoginViewModel
import com.wakeupngoc.kotlinboilerplate.ui.base.activity.BaseActivity
import com.wakeupngoc.kotlinboilerplate.ui.base.actions.Action
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    lateinit var loginVM: LoginViewModel
    @Inject lateinit var vmFactory: ViewModelProvider.Factory

    private val bin: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Injections must happen after so we are guaranteed to have config component injected!
        loginVM = ViewModelProviders.of(this, vmFactory).get(LoginViewModel::class.java)
        bindVM()
    }

    private fun bindVM() {

        loginVM.getLastInputEmail().subscribeBy(onSuccess= { lastEmail ->
            email.setText(lastEmail)
        })

        val emailObservable: Observable<String> = RxTextView.textChanges(email)
                .map { email ->
                    email.toString()
                }

        val passwordObservable: Observable<String> = RxTextView.textChanges(password)
                .map { password ->
                    password.toString()
                }

        // User Input Stream
        val userInputStream: Observable<LoginInputAction> = Observable
                .combineLatest(emailObservable, passwordObservable, BiFunction<String, String, LoginInputAction> {
                    email, password -> LoginInputAction(email, password)
                })

        // Our submit stream needs data from our most recent email/password input
        val submitInputStream: Observable<SubmitAction> = Observable
                .combineLatest(emailObservable, passwordObservable, BiFunction<String, String, SubmitAction> {
                    email, password -> SubmitAction(email, password)
                })

        // Click Stream
        val loginInputStream: Observable<SubmitAction>? = RxView.clicks(login_button)
                .withLatestFrom(submitInputStream, BiFunction<Any, SubmitAction, SubmitAction> { _, t2 -> t2 })

        // Combine both input and clicks into one stream
        val actionStream: Observable<Action> = Observable.merge(userInputStream, loginInputStream)

        // Subscribe to whole display state of this screen
        loginVM.getLoginDisplayState(actionStream)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onNext = {
                    (btnState, loginState, error) ->
                    login_button.isEnabled = btnState
                    onLoginState(loginState, error)
                })
                .into(bin)
    }

    @UiThread
    private fun onLoginState(loginState: LoginState, error: Throwable?) {
        when(loginState) {
            LoginState.LOGGED_OUT -> {
                if (error != null) {
                    auth_error.text = error.message
                    auth_error.visibility = View.VISIBLE
                } else {
                    auth_error.text = null
                    auth_error.visibility = View.GONE
                }
            }
            LoginState.LOGGING_IN -> {
                Toast.makeText(this, "I am logging in", Toast.LENGTH_LONG).show()
            }

            LoginState.LOGGED_IN -> {
                Toast.makeText(this, "I am logged in", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bin.dispose()
    }


}

