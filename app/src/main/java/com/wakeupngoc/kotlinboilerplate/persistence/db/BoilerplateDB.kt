package com.wakeupngoc.kotlinboilerplate.persistence.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.wakeupngoc.kotlinboilerplate.persistence.Account
import com.wakeupngoc.kotlinboilerplate.persistence.AccountsDao
import com.wakeupngoc.kotlinboilerplate.persistence.converters.DateTypeConverter

/**
 * Created by ngoctranfire on 8/13/17.
 */
@Database(entities = arrayOf(Account::class), version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class BoilerplateDB: RoomDatabase() {
    abstract fun accountsDao(): AccountsDao
}