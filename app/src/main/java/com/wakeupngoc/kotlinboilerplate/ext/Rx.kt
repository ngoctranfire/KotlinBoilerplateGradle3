
package com.wakeupngoc.kotlinboilerplate.ext

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by ngoctranfire on 8/7/17.
 */
fun Disposable.into(bin: CompositeDisposable) {
    bin.add(this)
}