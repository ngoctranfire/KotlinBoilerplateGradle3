package com.wakeupngoc.kotlinboilerplate.di.modules;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.wakeupngoc.kotlinboilerplate.di.multibinding.key.ViewModelKey;
import com.wakeupngoc.kotlinboilerplate.ui.auth.viewmodel.LoginViewModel;
import com.wakeupngoc.kotlinboilerplate.ui.base.viewmodel.BoilerplateViewModelFactory;

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
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindUserViewModel(LoginViewModel loginVM);

    @Binds
    abstract ViewModelProvider.Factory bindBoilerplateViewModelFactory(BoilerplateViewModelFactory factory);

}
