package com.wakeupngoc.kotlinboilerplate.ui.base.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by ngoctranfire on 7/29/17.
 */
abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate, savedInstanceState=%s", savedInstanceState)
    }

    override fun onAttachedToWindow() {
        Timber.d("onAttachedToWindow")
        super.onAttachedToWindow()
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        Timber.d("onActivityReenter, resultCode=%d, data=%s", resultCode, data)
        super.onActivityReenter(resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Timber.d("onCreateOptionsMenu, menu=%s", menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        Timber.d("onPostCreate, savedInstanceState=%s", savedInstanceState)
        super.onPostCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        Timber.d("onSaveInsaneState, outState=%s", outState)
        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        Timber.d("onPause")
        super.onPause()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        Timber.d("onRestoreInstanceState, savedInstanceState=%s", savedInstanceState)
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onResumeFragments() {
        Timber.d("onResumeFragments")
        super.onResumeFragments()
    }

    override fun onResume() {
        Timber.d("onResume")
        super.onResume()
    }

    override fun onDetachedFromWindow() {
        Timber.d("onDetachedFromWindow")
        super.onDetachedFromWindow()
    }
    
    override fun onStop() {
        Timber.d("onStop")
        super.onStop()
    }

    override fun onRestart() {
        Timber.d("onRestart")
        super.onRestart()
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
        super.onDestroy()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}