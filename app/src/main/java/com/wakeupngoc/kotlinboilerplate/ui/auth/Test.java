package com.wakeupngoc.kotlinboilerplate.ui.auth;

import com.wakeupngoc.kotlinboilerplate.ui.auth.models.input.UserLoginInputModel;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;


/**
 * Created by ngoctranfire on 8/10/17.
 */

public class Test {


    public void test() {
        Observable<String> hey = Observable.just("you");
        Observable<String> you = Observable.just("hey");
        Observable<Integer> you2 = Observable.just(3);

        Observable.combineLatest(hey, you, (BiFunction<String, String, UserLoginInputModel>) (s, s2) -> null);

//
////        ObservableTransformer<UserLoginInputModel, LoginDisplayState> transformer
////                = upstream -> upstream.switchMap(userLoginInputModel -> Observable.just(new LoginDisplayState(true, LoginState.LOGGED_IN, new AuthErrorState(null, 0))));
//
//
//        Observable.just("hey2")
//                .withLatestFrom(Observable.just(3L), new BiFunction<String, Long, String>() {
//                    @Override
//                    public String apply(String s, Long aLong) throws Exception {
//                        return "hey2";
//                    }
//                });
//        AuthService authService = new AuthService() {
//            @NotNull
//            @Override
//            public Maybe<AuthResponse> authenticate(@NotNull String email, @NotNull String password, @NotNull String strategy) {
//                return null;
//            }
//        };
//
//        authService.authenticate("hello", "bye", "whatever")
//                .flatMapObservable(authResponse -> Observable.just(new SubmitResult(true, false, null)))
//                .startWith(new SubmitResult(true, false, null));
//
//
//        hey.scan(new BiFunction<String, String, String>() {
//            @Override
//            public String apply(String s, String s2) throws Exception {
//                return null;
//            }
//        });

    }
}
