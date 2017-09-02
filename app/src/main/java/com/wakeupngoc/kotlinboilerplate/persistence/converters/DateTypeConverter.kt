package com.wakeupngoc.kotlinboilerplate.persistence.converters

import android.arch.persistence.room.TypeConverter
import java.util.Date

/**
 * Created by ngoctranfire on 8/13/17.
 */
class DateTypeConverter {
    companion object {
        @TypeConverter @JvmStatic
        fun toDate(value: Long?): Date? = if (value == null) null else Date(value)

        @TypeConverter @JvmStatic
        fun toLong(value: Date?): Long? = value?.time
    }

}