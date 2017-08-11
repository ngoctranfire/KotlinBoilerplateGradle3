package com.wakeupngoc.kotlinboilerplate.ui.login

import android.os.Bundle
import com.jakewharton.rxbinding2.widget.RxTextView
import com.wakeupngoc.kotlinboilerplate.R
import com.wakeupngoc.kotlinboilerplate.ext.into
import com.wakeupngoc.kotlinboilerplate.ui.base.activity.BaseActivity
import com.wakeupngoc.kotlinboilerplate.ui.login.models.input.UserLoginInputModel
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
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
        val emailObservable: Observable<String> = RxTextView.textChanges(email)
                .map { email -> email.toString() }

        val passwordObservable: Observable<String> = RxTextView.textChanges(password)
                .map { password -> password.toString() }

        val userInput: Observable<UserLoginInputModel> = Observable
                .combineLatest(emailObservable, passwordObservable, BiFunction<CharSequence, CharSequence, UserLoginInputModel> {
                    email, password ->
                        val userLoginInputModel = UserLoginInputModel(email.toString(), password.toString())
                        userLoginInputModel
                })

        vm.getButtonState(userInput)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    state -> login_button.isEnabled = state
                })
                .into(bin)
    }

    override fun onDestroy() {
        super.onDestroy()
        bin.dispose()
    }


}

