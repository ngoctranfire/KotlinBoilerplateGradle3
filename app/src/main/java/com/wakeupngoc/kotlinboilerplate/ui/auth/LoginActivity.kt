package com.wakeupngoc.kotlinboilerplate.ui.auth

import android.os.Bundle
import com.jakewharton.rxbinding2.widget.RxTextView
import com.wakeupngoc.kotlinboilerplate.R
import com.wakeupngoc.kotlinboilerplate.ext.into
import com.wakeupngoc.kotlinboilerplate.ui.auth.models.display.LoginDisplayState
import com.wakeupngoc.kotlinboilerplate.ui.base.activity.BaseActivity
import com.wakeupngoc.kotlinboilerplate.ui.auth.models.input.UserLoginInputModel
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @Inject lateinit var vm: LoginVM

    private val bin: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindVM()
    }

    private fun bindVM() {
        bindButtonState()

    }

    private fun bindButtonState() {
        val emailObservable: Observable<String> = RxTextView.afterTextChangeEvents(email)
                .map { email -> email.toString() }

        val passwordObservable: Observable<String> = RxTextView.afterTextChangeEvents(password)
                .map { password -> password.toString() }
        val userInput: Observable<UserLoginInputModel> = Observable
                .combineLatest(emailObservable, passwordObservable, BiFunction<CharSequence, CharSequence, UserLoginInputModel> {
                    email, password ->
                        val userLoginInputModel = UserLoginInputModel(email.toString(), password.toString())
                        userLoginInputModel
                })


        vm.getLoginDisplayState(userInput)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onNext = {
                    (btnState) -> login_button.isEnabled = btnState
                })
                .into(bin)

    }

    override fun onDestroy() {
        super.onDestroy()
        bin.dispose()
    }


}

