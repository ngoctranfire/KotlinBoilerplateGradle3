package com.wakeupngoc.kotlinboilerplate.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.wakeupngoc.kotlinboilerplate.di.multibinding.key.ViewModelKey;
import com.wakeupngoc.kotlinboilerplate.ui.auth.LoginVMImpl;
import com.wakeupngoc.kotlinboilerplate.ui.base.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by ngoctranfire on 9/7/17.
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginVMImpl.class)
    abstract ViewModel bindUserViewModel(LoginVMImpl loginVM);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
