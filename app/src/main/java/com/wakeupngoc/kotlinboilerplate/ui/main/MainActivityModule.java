package com.wakeupngoc.kotlinboilerplate.ui.main;


import com.wakeupngoc.kotlinboilerplate.di.scopes.ActScope;

import dagger.Binds;
import dagger.Module;

/**
 * Created by ngoctranfire on 8/11/17.
 */

@Module @ActScope
abstract public class MainActivityModule {

    @Binds
    abstract MainVM providesMainVM(MainVMImpl mainVM);
}
