package com.wakeupngoc.kotlinboilerplate.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wakeupngoc.kotlinboilerplate.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
