package com.wakeupngoc.kotlinboilerplate.persistence.local.db.tables

import android.arch.persistence.room.*
import io.reactivex.Flowable
import java.util.*

/**
 * Created by ngoctranfire on 8/13/17.
 */
@Entity(tableName = "accounts")
data class Account(
        @PrimaryKey
        val email: String,
        val lastLoginDate: Date
)

@Dao
interface AccountsDao {

    @Query("SELECT * FROM accounts")
    fun getAccounts(): Flowable<List<Account>>

    @Insert
    fun insert(account: Account)

}