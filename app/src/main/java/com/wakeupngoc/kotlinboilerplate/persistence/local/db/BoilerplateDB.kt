package com.wakeupngoc.kotlinboilerplate.persistence.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.wakeupngoc.kotlinboilerplate.persistence.local.converters.DateTypeConverter
import com.wakeupngoc.kotlinboilerplate.persistence.local.db.tables.Account
import com.wakeupngoc.kotlinboilerplate.persistence.local.db.tables.AccountsDao

/**
 * Created by ngoctranfire on 8/13/17.
 */
@Database(entities = arrayOf(Account::class), version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class BoilerplateDB: RoomDatabase() {
    abstract fun accountsDao(): AccountsDao
}