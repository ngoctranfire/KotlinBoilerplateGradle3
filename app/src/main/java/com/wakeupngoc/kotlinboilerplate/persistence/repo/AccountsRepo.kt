package com.wakeupngoc.kotlinboilerplate.persistence.repo

import com.wakeupngoc.kotlinboilerplate.persistence.local.db.BoilerplateDB
import com.wakeupngoc.kotlinboilerplate.persistence.local.db.tables.Account
import com.wakeupngoc.kotlinboilerplate.persistence.local.db.tables.AccountsDao
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by ngoctranfire on 9/4/17.
 */
interface AccountsStore {
    fun getAccounts(): Flowable<List<Account>>
    fun insertAccount(account: Account): Completable
}

class AccountsRepo @Inject constructor(
        boilerplateDB: BoilerplateDB
): AccountsStore {
    private val accountsDao: AccountsDao = boilerplateDB.accountsDao()

    override fun getAccounts(): Flowable<List<Account>> {
        return accountsDao.getAccounts()
    }

    override fun insertAccount(account: Account): Completable {
        return Completable.fromCallable {
            accountsDao.insert(account)
        }.subscribeOn(Schedulers.io())
    }

}