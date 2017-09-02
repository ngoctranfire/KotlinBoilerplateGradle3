package com.wakeupngoc.kotlinboilerplate.ui.auth;

import com.wakeupngoc.kotlinboilerplate.definitions.auth.LoginState;
import com.wakeupngoc.kotlinboilerplate.ui.auth.models.display.LoginDisplayState;
import com.wakeupngoc.kotlinboilerplate.ui.auth.models.input.UserLoginInputModel;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.BiFunction;


/**
 * Created by ngoctranfire on 8/10/17.
 */

public class Test {


    public void test() {
        Observable<String> hey = Observable.just("you");
        Observable<String> you = Observable.just("hey");

        Observable.combineLatest(hey, you, (BiFunction<String, String, UserLoginInputModel>) (s, s2) -> null);


        ObservableTransformer<UserLoginInputModel, LoginDisplayState> transformer
                = upstream -> upstream.switchMap(userLoginInputModel -> Observable.just(new LoginDisplayState(true, LoginState.LOGGED_IN)));
    }
}
