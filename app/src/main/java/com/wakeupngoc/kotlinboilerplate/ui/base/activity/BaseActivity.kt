package com.wakeupngoc.kotlinboilerplate.ui.base.activity

import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.LifecycleRegistry

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import timber.log.Timber

/**
 * Created by ngoctranfire on 7/29/17.
 */
abstract class BaseActivity : AppCompatActivity(), LifecycleRegistryOwner {
    @Suppress("LeakingThis")
    private val lifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate, savedInstanceState=%s", savedInstanceState)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Timber.d("onAttachedToWindow")
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)
        Timber.d("onActivityReenter, resultCode=%d, data=%s", resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Timber.d("onCreateOptionsMenu, menu=%s", menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        Timber.d("onPostCreate, savedInstanceState=%s", savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Timber.d("onSaveInsaneState, outState=%s", outState)
    }

    override fun onPause() {
        super.onPause()
        Timber.d("onPause")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.d("onRestoreInstanceState, savedInstanceState=%s", savedInstanceState)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        Timber.d("onResumeFragments")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Timber.d("onDetachedFromWindow")
    }
    
    override fun onStop() {
        super.onStop()
        Timber.d("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.d("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy")
    }

}