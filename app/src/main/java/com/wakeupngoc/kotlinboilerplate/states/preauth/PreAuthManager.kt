package com.wakeupngoc.kotlinboilerplate.states.preauth

import com.wakeupngoc.kotlinboilerplate.persistence.local.db.BoilerplateDB
import javax.inject.Inject

/**
 * Created by ngoctranfire on 8/13/17.
 */
interface AccountState {

}

class AccountManager @Inject constructor(boilerplateDB: BoilerplateDB): AccountState {
    init {

    }
}