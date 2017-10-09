package com.wakeupngoc.kotlinboilerplate.ui.auth.models.result

import com.wakeupngoc.kotlinboilerplate.ui.base.result.Result

data class SubmitResult(val inProgress: Boolean, val success: Boolean, val error: Throwable?) : Result() {
    companion object {
        fun IN_PROGRESS(): SubmitResult {
            return SubmitResult(true, false, null)
        }
        fun SUCCESS(): SubmitResult {
            return SubmitResult(false, true, null)
        }
        fun FAILED(t: Throwable): SubmitResult {
            return SubmitResult(false, false, t)
        }
    }
}
