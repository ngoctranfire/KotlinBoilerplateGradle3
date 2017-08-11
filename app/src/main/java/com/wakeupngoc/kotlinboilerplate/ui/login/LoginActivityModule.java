package com.wakeupngoc.kotlinboilerplate.ui.login;


import com.wakeupngoc.kotlinboilerplate.di.scopes.ActScope;

import dagger.Binds;
import dagger.Module;

/**
 * Created by ngoctranfire on 8/11/17.
 */

@Module @ActScope
abstract public class LoginActivityModule {

    @Binds
    abstract LoginVM providesLoginVM(LoginVMImpl loginVM);
}
