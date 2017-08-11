package com.wakeupngoc.kotlinboilerplate.ui.login;

import com.wakeupngoc.kotlinboilerplate.ui.login.models.input.UserLoginInputModel;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

/**
 * Created by ngoctranfire on 8/10/17.
 */

public class Test {


    public void test() {
        Observable<String> hey = Observable.just("you");
        Observable<String> you = Observable.just("hey");

        Observable.combineLatest(hey, you, new BiFunction<String, String, UserLoginInputModel>() {
            @Override
            public UserLoginInputModel apply(String s, String s2) throws Exception {
                return null;
            }
        });

        Observable.just("hey")
                .switchMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return Observable.just("hey");
                    }
                });
    }
}
